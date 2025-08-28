package com.example.tracker.domain.usecases

import com.example.tracker.domain.models.SeasonInfoEntity
import com.example.tracker.domain.repository.TrackerRepository
import javax.inject.Inject

class GetCurrentSeasonUseCase @Inject constructor (
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(): SeasonInfoEntity {
        return repository.getCurrentSeason()
    }
}