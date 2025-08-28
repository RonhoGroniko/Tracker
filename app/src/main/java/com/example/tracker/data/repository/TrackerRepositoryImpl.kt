package com.example.tracker.data.repository

import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import com.example.tracker.data.db.PlayerDao
import com.example.tracker.data.db.PlayerStatsDao
import com.example.tracker.data.db.SeasonDao
import com.example.tracker.data.mappers.toPlayerDbModel
import com.example.tracker.data.mappers.toPlayerInfoEntity
import com.example.tracker.data.mappers.toPlayerSeasonGameModeStatsEntity
import com.example.tracker.data.mappers.toPlayerStatsDbModel
import com.example.tracker.data.mappers.toSeasonDbModel
import com.example.tracker.data.mappers.toSeasonInfoEntity
import com.example.tracker.data.mappers.toSeasonInfoEntityList
import com.example.tracker.data.network.ApiService
import com.example.tracker.data.workers.RefreshSeasonsWorker
import com.example.tracker.domain.models.PlayerInfoEntity
import com.example.tracker.domain.models.PlayerSeasonGameModeStatsEntity
import com.example.tracker.domain.models.SeasonInfoEntity
import com.example.tracker.domain.repository.TrackerRepository
import javax.inject.Inject

class TrackerRepositoryImpl @Inject constructor (
    private val application: Application,
    private val apiService: ApiService,
    private val seasonDao: SeasonDao,
    private val playerDao: PlayerDao,
    private val playerStatsDao: PlayerStatsDao
): TrackerRepository {

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

    override suspend fun loadSeasons() {
        WorkManager.getInstance(application)
            .enqueueUniquePeriodicWork(
                RefreshSeasonsWorker.NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                RefreshSeasonsWorker.makeRequest()
            )
    }
}