package com.example.tracker.domain.usecases

import com.example.tracker.domain.models.SeasonInfoEntity
import com.example.tracker.domain.repository.TrackerRepository

class GetSeasonByNameUseCase(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(seasonName: String): SeasonInfoEntity {
        return repository.getSeasonByName(seasonName)
    }
}