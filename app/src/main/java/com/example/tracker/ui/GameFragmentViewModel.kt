package com.example.tracker.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tracker.data.db.AppDatabase
import com.example.tracker.data.mappers.toSeasonDbModel
import com.example.tracker.data.repository.TrackerRepositoryImpl
import com.example.tracker.domain.usecases.GetPlayerByNameUseCase
import com.example.tracker.domain.usecases.GetSeasonListUseCase
import kotlinx.coroutines.launch

class GameFragmentViewModel(application: Application): AndroidViewModel(application = application) {

    private val repository = TrackerRepositoryImpl(application)
    private val seasonDao = AppDatabase.getInstance(application).seasonDao()

    private val getPlayerByNameUseCase = GetPlayerByNameUseCase(repository)
    private val getSeasonListUseCase = GetSeasonListUseCase(repository)

    private val _inputText = MutableLiveData<String>()
    val inputText: LiveData<String>
        get() = _inputText

    private val _errorLD = MutableLiveData<Boolean>()
    val errorLD: LiveData<Boolean>
        get() = _errorLD

    fun loadPlayer() {
        viewModelScope.launch {
            try {
                val playerName = parseName(_inputText.value)
                if (validateInputName(playerName)) {
                    val playerInfo = getPlayerByNameUseCase(playerName)
                    Log.d(TAG, playerInfo.name)
                }
            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
            }
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

    companion object {

        private const val TAG = "GameFragmentViewModel"
    }
}