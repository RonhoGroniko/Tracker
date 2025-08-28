package com.example.tracker.domain.usecases

import com.example.tracker.domain.models.PlayerSeasonGameModeStatsEntity
import com.example.tracker.domain.repository.TrackerRepository
import javax.inject.Inject

class AddPlayerStatsUseCase @Inject constructor  (
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(playerId: String, seasonId: String, playerStats: PlayerSeasonGameModeStatsEntity) {
        repository.addPlayerStats(playerId, seasonId, playerStats)
    }
}