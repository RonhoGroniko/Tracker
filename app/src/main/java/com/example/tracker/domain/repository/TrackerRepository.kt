package com.example.tracker.domain.repository

import com.example.tracker.data.network.models.player_season_response.GameMode
import com.example.tracker.domain.models.PlayerInfoEntity
import com.example.tracker.domain.models.PlayerSeasonInfoEntity

import com.example.tracker.domain.models.SeasonInfoEntity

interface TrackerRepository {

    suspend fun getPlayer(playerName: String): PlayerInfoEntity

    suspend fun getSeasons(): List<SeasonInfoEntity>

    suspend fun getCurrentSeason(): SeasonInfoEntity

    suspend fun getPlayerSeasonInfo(playerID: String, seasonID: String, gameMode: GameMode): PlayerSeasonInfoEntity
}