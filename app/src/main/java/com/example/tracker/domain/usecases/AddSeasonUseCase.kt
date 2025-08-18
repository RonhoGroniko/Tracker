package com.example.tracker.domain.usecases

import com.example.tracker.domain.models.SeasonInfoEntity
import com.example.tracker.domain.repository.TrackerRepository

class AddSeasonUseCase(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(seasonInfoEntity: SeasonInfoEntity) {
        repository.addSeason(seasonInfoEntity)
    }
}