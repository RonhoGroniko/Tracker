package com.example.tracker.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tracker.data.mappers.toPlayerInfoUiModel
import com.example.tracker.data.mappers.toPlayerSeasonGameModeStatsUiModel
import com.example.tracker.domain.models.PlayerInfoEntity
import com.example.tracker.domain.usecases.AddPlayerStatsUseCase
import com.example.tracker.domain.usecases.AddPlayerUseCase
import com.example.tracker.domain.usecases.GetCurrentSeasonUseCase
import com.example.tracker.domain.usecases.GetPlayerByNameUseCase
import com.example.tracker.domain.usecases.GetPlayerSeasonInfoUseCase
import com.example.tracker.domain.usecases.LoadSeasonsUseCase
import com.example.tracker.ui.models.FullPlayerInfo
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class GameViewModel @Inject constructor (
    private val loadSeasonsUseCase: LoadSeasonsUseCase,
    private val addPlayerUseCase: AddPlayerUseCase,
    private val addPlayerStatsUseCase: AddPlayerStatsUseCase,
    private val getPlayerByNameUseCase: GetPlayerByNameUseCase,
    private val getCurrentSeasonUseCase: GetCurrentSeasonUseCase,
    private val getPlayerSeasonInfoUseCase: GetPlayerSeasonInfoUseCase,
) : ViewModel() {

    private val _inputText = MutableLiveData<String>()
    val inputText: LiveData<String>
        get() = _inputText

    private val _errorLD = MutableLiveData<Boolean>()
    val errorLD: LiveData<Boolean>
        get() = _errorLD

    private val _errorRequestLD = MutableLiveData<Boolean>()
    val errorRequestLD: LiveData<Boolean>
        get() = _errorRequestLD

    private val _isLoadingLD = MutableLiveData(false)
    val isLoadingLD: LiveData<Boolean>
        get() = _isLoadingLD

    private val _fullPlayerInfo = MutableLiveData<FullPlayerInfo?>()
    val fullPlayerInfo: LiveData<FullPlayerInfo?>
        get() = _fullPlayerInfo

    private suspend fun loadPlayer(): PlayerInfoEntity? {
        _errorRequestLD.value = false
        try {
            val playerName = parseName(_inputText.value)
            if (validateInputName(playerName)) {
                val playerInfo = getPlayerByNameUseCase(playerName)
                addPlayerUseCase(playerInfo)
                return playerInfo
            } else {
                return null
            }
        } catch (e: Exception) {
            Log.d(TAG, e.message.toString())
            _errorRequestLD.value = true
            return null
        }
    }

    fun loadCurrentSeasonPLayerInfo() {
        viewModelScope.launch {
            val loading = _isLoadingLD.value
            if (loading != null && loading) {
                return@launch
            } else {
                _isLoadingLD.value = true
                _errorRequestLD.value = false
            }
            val seasonDeferred = async { getCurrentSeasonUseCase().id }
            val loadPlayerDeferred = async { loadPlayer() }

            val seasonID = seasonDeferred.await()
            val playerInfo = loadPlayerDeferred.await()
            if (playerInfo != null) {
                try {
                    val playerSeasonInfo = getPlayerSeasonInfoUseCase(playerInfo.id, seasonID)
                    addPlayerStatsUseCase(
                        playerId = playerInfo.id,
                        seasonId = seasonID,
                        playerStats = playerSeasonInfo
                    )
                    _fullPlayerInfo.value = _fullPlayerInfo.value?.copy(
                        playerInfo = playerInfo.toPlayerInfoUiModel(),
                        stats = playerSeasonInfo.toPlayerSeasonGameModeStatsUiModel()
                    ) ?: FullPlayerInfo(
                        playerInfo.toPlayerInfoUiModel(),
                        playerSeasonInfo.toPlayerSeasonGameModeStatsUiModel()
                    )
                    Log.d(TAG, fullPlayerInfo.value.toString())
                } catch (e: Exception) {
                    Log.e(TAG, "Error loading player season info", e)
                    _errorRequestLD.value = true
                }
            } else {
                _isLoadingLD.value = false
            }
            _isLoadingLD.value = false
        }
    }

    private fun parseName(str: String?): String {
        return str?.trim() ?: ""
    }

    private fun validateInputName(name: String): Boolean {
        if (name.isEmpty()) {
            _errorLD.value = true
            return false
        }
        _errorLD.value = false
        return true
    }

    fun changeString(string: String) {
        _inputText.value = string
    }

    fun resetInputNameError() {
        _errorLD.value = false
    }

    fun resetPlayerSeasonInfo() {
        _fullPlayerInfo.value = null
    }

    init {
        viewModelScope.launch {
            loadSeasonsUseCase()
        }
    }

    companion object {

        private const val TAG = "GameFragmentViewModel"
    }
}