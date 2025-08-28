package com.example.tracker.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tracker.common.enums.GameMode
import com.example.tracker.data.mappers.toPlayerSeasonGameModeStatsUiModel
import com.example.tracker.data.mappers.toSeasonInfoUiModel
import com.example.tracker.data.mappers.toStatItems
import com.example.tracker.domain.usecases.GetCurrentSeasonUseCase
import com.example.tracker.domain.usecases.GetPlayerSeasonInfoUseCase
import com.example.tracker.domain.usecases.GetSeasonByNameUseCase
import com.example.tracker.domain.usecases.GetSeasonListUseCase
import com.example.tracker.ui.models.PlayerSeasonGameModeStatsUiModel
import com.example.tracker.ui.models.SeasonInfoUiModel
import com.example.tracker.ui.models.StatItemUiModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class GameStatsViewModel @Inject constructor(
    private val getSeasonListUseCase: GetSeasonListUseCase,
    private val getCurrentSeasonUseCase: GetCurrentSeasonUseCase,
    private val getPlayerSeasonInfoUseCase: GetPlayerSeasonInfoUseCase,
    private val getSeasonByNameUseCase: GetSeasonByNameUseCase
) : ViewModel() {

    private var playerId: String? = null

    fun initPlayer(id: String) {
        playerId = id
    }

    private val _gameModeList = MutableLiveData<List<GameMode>>(GameMode.entries)
    val gameModeList: LiveData<List<GameMode>>
        get() = _gameModeList

    private val _gameMode = MutableLiveData<GameMode>()
    val gameMode: LiveData<GameMode>
        get() = _gameMode

    private val _seasons = MutableLiveData<List<SeasonInfoUiModel>>()
    val seasons: LiveData<List<SeasonInfoUiModel>>
        get() = _seasons

    private val _selectedSeason = MutableLiveData<String>()
    val selectedSeason: LiveData<String>
        get() = _selectedSeason

    private val _playerSeasonInfo = MutableLiveData<PlayerSeasonGameModeStatsUiModel>()
    val playerSeasonInfo: LiveData<PlayerSeasonGameModeStatsUiModel>
        get() = _playerSeasonInfo

    val currentStats: LiveData<List<StatItemUiModel>> =
        MediatorLiveData<List<StatItemUiModel>>().apply {
            fun update() {
                val playerStats = _playerSeasonInfo.value
                val gameMode = _gameMode.value
                if (playerStats != null && gameMode != null) {
                    value = when (gameMode) {
                        GameMode.DUO -> playerStats.gameModeStats.duo.toStatItems()
                        GameMode.DUO_FPP -> playerStats.gameModeStats.duoFpp.toStatItems()
                        GameMode.SOLO -> playerStats.gameModeStats.solo.toStatItems()
                        GameMode.SOLO_FPP -> playerStats.gameModeStats.soloFpp.toStatItems()
                        GameMode.SQUAD -> playerStats.gameModeStats.squad.toStatItems()
                        GameMode.SQUAD_FPP -> playerStats.gameModeStats.squadFpp.toStatItems()
                    }
                }
            }
            addSource(playerSeasonInfo) { update() }
            addSource(gameMode) { update() }
        }

    private fun getSeasons() {
        viewModelScope.launch {
            try {
                _seasons.value = getSeasonListUseCase().map { it.toSeasonInfoUiModel() }
                    .map {
                        it.copy(isSelected = it.name == _selectedSeason.value)
                    }
                Log.d("GameStatsViewModel", _seasons.value.toString())
            } catch (e: Exception) {
                Log.d("GameStatsViewModel", e.message.toString())
            }
        }
    }

    private fun getCurrentSeason() {
        viewModelScope.launch {
            try {
                val current = getCurrentSeasonUseCase().name
                if (_selectedSeason.value.isNullOrEmpty()) {
                    setSelectedSeason(current)
                }
            } catch (e: Exception) {
                Log.e("GameStatsViewModel", e.message.toString(), e)
            }
        }
    }

    fun loadPlayerSeasonInfo(playerId: String, seasonName: String) {
        viewModelScope.launch {
            val seasonIdDeferred = async { getSeasonByNameUseCase(seasonName).id }
            val seasonId = seasonIdDeferred.await()
            getStats(playerId, seasonId)
        }
    }

    private fun getStats(playerId: String, seasonId: String) {
        viewModelScope.launch {
            try {
                val playerStats = getPlayerSeasonInfoUseCase(playerId, seasonId)
                _playerSeasonInfo.value = playerStats.toPlayerSeasonGameModeStatsUiModel()
            } catch (e: Exception) {
                Log.d("GameStatsViewModel", e.message.toString())
            }
        }
    }

    fun setGameMode(gameMode: GameMode) {
        _gameMode.value = gameMode
    }

    fun setSelectedSeason(seasonName: String) {
        _selectedSeason.value = seasonName
        _seasons.value?.let { seasons ->
            _seasons.value = seasons.map {
                it.copy(isSelected = it.name == seasonName)
            }
        }
        Log.d("GameStatsViewModel", "setSelected" + _seasons.value.toString())
    }

    init {
        getCurrentSeason()
        getSeasons()
        setGameMode(GameMode.SOLO_FPP)
        _selectedSeason.observeForever { seasonName ->
            val id = playerId ?: return@observeForever
            loadPlayerSeasonInfo(id, seasonName)
        }
    }
}
