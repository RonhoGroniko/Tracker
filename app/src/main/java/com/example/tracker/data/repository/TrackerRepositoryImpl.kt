package com.example.tracker.data.repository

import com.example.tracker.data.mappers.PlayerMapper
import com.example.tracker.data.network.ApiFactory
import com.example.tracker.domain.models.PlayerInfoEntity
import com.example.tracker.domain.repository.TrackerRepository

object TrackerRepositoryImpl: TrackerRepository {

    private val apiService = ApiFactory.apiService
    private val mapper = PlayerMapper()

    override suspend fun getPlayer(playerName: String): PlayerInfoEntity {
        return mapper.mapDtoToEntity(apiService.getPlayerByName(playerName))
    }
}