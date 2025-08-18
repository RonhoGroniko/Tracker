package com.example.tracker.domain.repository

import com.example.tracker.domain.models.PlayerInfoEntity
import com.example.tracker.domain.models.PlayerSeasonGameModeStatsEntity
import com.example.tracker.domain.models.SeasonInfoEntity

interface TrackerRepository {

    suspend fun getPlayer(playerName: String): PlayerInfoEntity

    suspend fun getSeasons(): List<SeasonInfoEntity>

    suspend fun getCurrentSeason(): SeasonInfoEntity

    suspend fun getPlayerSeasonInfo(playerID: String, seasonID: String): PlayerSeasonGameModeStatsEntity

    suspend fun getSeasonByName(seasonName: String): SeasonInfoEntity

    suspend fun addSeason(seasonInfoEntity: SeasonInfoEntity)

    suspend fun addPlayerStats(playerId: String, seasonId: String, playerStats: PlayerSeasonGameModeStatsEntity)

    suspend fun addPlayer(player: PlayerInfoEntity)
}