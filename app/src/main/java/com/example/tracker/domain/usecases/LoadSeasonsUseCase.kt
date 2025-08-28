package com.example.tracker.domain.usecases

import com.example.tracker.domain.repository.TrackerRepository
import javax.inject.Inject

class LoadSeasonsUseCase @Inject constructor(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke() {
        repository.loadSeasons()
    }
}