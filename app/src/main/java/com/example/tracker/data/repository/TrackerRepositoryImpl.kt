package com.example.tracker.data.repository

import android.app.Application
import com.example.tracker.data.db.AppDatabase
import com.example.tracker.data.mappers.toPlayerInfoEntity
import com.example.tracker.data.mappers.toPlayerSeasonGameModeStatsEntity
import com.example.tracker.data.mappers.toSeasonInfoEntity
import com.example.tracker.data.mappers.toSeasonInfoEntityList
import com.example.tracker.data.network.ApiFactory
import com.example.tracker.domain.models.PlayerInfoEntity
import com.example.tracker.domain.models.PlayerSeasonGameModeStatsEntity
import com.example.tracker.domain.models.SeasonInfoEntity
import com.example.tracker.domain.repository.TrackerRepository

class TrackerRepositoryImpl(application: Application): TrackerRepository {

    private val apiService = ApiFactory.apiService
    private val seasonDao = AppDatabase.getInstance(application).seasonDao()

    override suspend fun getPlayer(playerName: String): PlayerInfoEntity {
        return apiService.getPlayerByName(playerName).toPlayerInfoEntity()
    }

    override suspend fun getSeasons(): List<SeasonInfoEntity> {
        return apiService.getSeasons().toSeasonInfoEntityList()
    }

    override suspend fun getCurrentSeason(): SeasonInfoEntity {
        return seasonDao.getCurrentSeason().toSeasonInfoEntity()
    }

    override suspend fun getPlayerSeasonInfo(
        playerID: String,
        seasonID: String
    ): PlayerSeasonGameModeStatsEntity {
        return apiService.getPlayerSeasonInfo(
            playerID,
            seasonID
        ).data.attributes.toPlayerSeasonGameModeStatsEntity()
    }
}