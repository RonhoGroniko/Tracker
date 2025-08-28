package com.example.tracker.domain.usecases

import com.example.tracker.domain.models.PlayerSeasonGameModeStatsEntity
import com.example.tracker.domain.repository.TrackerRepository

class GetPlayerSeasonInfoUseCase(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(playerID: String, seasonID: String): PlayerSeasonGameModeStatsEntity {
        return repository.getPlayerSeasonInfo(playerID, seasonID)
    }
}