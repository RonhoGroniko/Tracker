package com.example.tracker.data.repository

import android.app.Application
import android.util.Log
import com.example.tracker.data.db.AppDatabase
import com.example.tracker.data.mappers.toPlayerDbModel
import com.example.tracker.data.mappers.toPlayerInfoEntity
import com.example.tracker.data.mappers.toPlayerSeasonGameModeStatsEntity
import com.example.tracker.data.mappers.toPlayerStatsDbModel
import com.example.tracker.data.mappers.toSeasonDbModel
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
    private val playerDao = AppDatabase.getInstance(application).playerDao()
    private val playerStatsDao = AppDatabase.getInstance(application).playerStatsDao()

    override suspend fun getPlayer(playerName: String): PlayerInfoEntity {
        val cachedPlayer = playerDao.getPlayerByName(playerName)
        if (cachedPlayer != null) {
            return cachedPlayer.toPlayerInfoEntity()
        }
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
        val cachedStats = playerStatsDao.getPlayerStats(playerID, seasonID)
        if (cachedStats != null) {
            return cachedStats.toPlayerSeasonGameModeStatsEntity()
        }
        return apiService.getPlayerSeasonInfo(
            playerID,
            seasonID
        ).data.attributes.toPlayerSeasonGameModeStatsEntity()
    }

    override suspend fun getSeasonByName(seasonName: String): SeasonInfoEntity {
        return seasonDao.getSeasonByName(seasonName).toSeasonInfoEntity()
    }

    override suspend fun addSeason(seasonInfoEntity: SeasonInfoEntity) {
        seasonDao.addSeason(seasonInfoEntity.toSeasonDbModel())
    }

    override suspend fun addPlayerStats(playerId: String, seasonId: String, playerStats: PlayerSeasonGameModeStatsEntity) {
        playerStatsDao.addPlayerStats(playerStats.toPlayerStatsDbModel(playerId, seasonId))
    }

    override suspend fun addPlayer(player: PlayerInfoEntity) {
        playerDao.addPlayer(player.toPlayerDbModel())
    }
}