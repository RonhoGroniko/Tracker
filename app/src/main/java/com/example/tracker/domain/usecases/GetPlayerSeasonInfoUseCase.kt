package com.example.tracker.domain.usecases

import com.example.tracker.data.network.models.player_season_response.GameMode
import com.example.tracker.domain.models.PlayerSeasonInfoEntity
import com.example.tracker.domain.repository.TrackerRepository

class GetPlayerSeasonInfoUseCase(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(playerID: String, seasonID: String, gameMode: GameMode): PlayerSeasonInfoEntity {
        return repository.getPlayerSeasonInfo(playerID, seasonID, gameMode)
    }
}