package com.example.tracker.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tracker.data.repository.TrackerRepositoryImpl
import com.example.tracker.domain.usecases.GetPlayerByNameUseCase
import kotlinx.coroutines.launch

class GameFragmentViewModel: ViewModel() {

    private val repository = TrackerRepositoryImpl
    private val getPlayerByNameUseCase = GetPlayerByNameUseCase(repository)

    private val _inputText = MutableLiveData<String>()
    val inputText: LiveData<String>
        get() = _inputText

    fun getPlayer() {
        viewModelScope.launch {
            val name = _inputText.value ?: ""
            val playerInfo = getPlayerByNameUseCase(name)
            Log.d("GameFragmentViewModel", playerInfo.name)
        }
    }

    fun changeString(string: String) {
        _inputText.value = string
    }
}