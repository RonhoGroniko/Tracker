package com.example.tracker.domain.usecases

import com.example.tracker.domain.models.PlayerSeasonGameModeStatsEntity
import com.example.tracker.domain.repository.TrackerRepository

class AddPlayerStatsUseCase(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(playerId: String, seasonId: String, playerStats: PlayerSeasonGameModeStatsEntity) {
        repository.addPlayerStats(playerId, seasonId, playerStats)
    }
}