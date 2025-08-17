package com.example.tracker.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tracker.data.db.AppDatabase
import com.example.tracker.data.mappers.toSeasonInfoUiModel
import com.example.tracker.data.repository.TrackerRepositoryImpl
import com.example.tracker.domain.usecases.GetSeasonListUseCase
import com.example.tracker.ui.models.SeasonInfoUiModel
import kotlinx.coroutines.launch

class GameStatsViewModel(application: Application): AndroidViewModel(application = application) {

    private val repository = TrackerRepositoryImpl(application)
    private val getSeasonListUseCase = GetSeasonListUseCase(repository)

    private val seasonDao = AppDatabase.getInstance(application).seasonDao()

    private val _seasons = MutableLiveData<List<SeasonInfoUiModel>>()
    val seasons: LiveData<List<SeasonInfoUiModel>>
        get() = _seasons

    private fun getSeasons() {
        viewModelScope.launch {
            _seasons.value = getSeasonListUseCase().map { it.toSeasonInfoUiModel() }
        }
    }

    init {
        getSeasons()
    }
}