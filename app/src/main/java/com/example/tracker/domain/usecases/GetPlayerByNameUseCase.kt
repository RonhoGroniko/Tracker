package com.example.tracker.domain.usecases

import com.example.tracker.domain.models.PlayerInfoEntity
import com.example.tracker.domain.repository.TrackerRepository

class GetPlayerByNameUseCase(private val repository: TrackerRepository) {

    suspend operator fun invoke(playerName: String): PlayerInfoEntity {
        return repository.getPlayer(playerName = playerName)
    }
}