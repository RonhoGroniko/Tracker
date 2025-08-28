package com.example.tracker.domain.usecases

import com.example.tracker.domain.models.PlayerInfoEntity
import com.example.tracker.domain.repository.TrackerRepository

class AddPlayerUseCase(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(player: PlayerInfoEntity) {
        repository.addPlayer(player)
    }
}