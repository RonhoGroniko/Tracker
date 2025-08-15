package com.example.tracker.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tracker.data.db.AppDatabase
import com.example.tracker.data.mappers.toPlayerSeasonGameModeStatsUiModel
import com.example.tracker.data.mappers.toSeasonDbModel
import com.example.tracker.data.repository.TrackerRepositoryImpl
import com.example.tracker.domain.models.PlayerInfoEntity
import com.example.tracker.domain.usecases.GetCurrentSeasonUseCase
import com.example.tracker.domain.usecases.GetPlayerByNameUseCase
import com.example.tracker.domain.usecases.GetPlayerSeasonInfoUseCase
import com.example.tracker.domain.usecases.GetSeasonListUseCase
import com.example.tracker.ui.models.PlayerSeasonGameModeStatsUiModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class GameFragmentViewModel(application: Application) :
    AndroidViewModel(application = application) {

    private val repository = TrackerRepositoryImpl(application)
    private val seasonDao = AppDatabase.getInstance(application).seasonDao()

    private val getPlayerByNameUseCase = GetPlayerByNameUseCase(repository)
    private val getSeasonListUseCase = GetSeasonListUseCase(repository)
    private val getCurrentSeasonUseCase = GetCurrentSeasonUseCase(repository)
    private val getPlayerSeasonInfoUseCase = GetPlayerSeasonInfoUseCase(repository)

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

    private val _playerSeasonInfo = MutableLiveData<PlayerSeasonGameModeStatsUiModel?>()
    val playerSeasonInfo: LiveData<PlayerSeasonGameModeStatsUiModel?>
        get() = _playerSeasonInfo

    private suspend fun loadPlayer(): PlayerInfoEntity? {
        _errorRequestLD.value = false
        try {
            val playerName = parseName(_inputText.value)
            if (validateInputName(playerName)) {
                val playerInfo = getPlayerByNameUseCase(playerName)
                Log.d(TAG, playerInfo.name)
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

    fun loadSeasons() {
        viewModelScope.launch {
            try {
                getSeasonListUseCase().forEach {
                    seasonDao.addSeason(it.toSeasonDbModel())
                }
            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
            }
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
                    _playerSeasonInfo.value = playerSeasonInfo.toPlayerSeasonGameModeStatsUiModel()
                    Log.d(TAG, playerSeasonInfo.toString())
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
        _playerSeasonInfo.value = null
    }

    companion object {

        private const val TAG = "GameFragmentViewModel"
    }
}