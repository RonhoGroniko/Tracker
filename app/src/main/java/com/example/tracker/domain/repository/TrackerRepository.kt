package com.example.tracker.domain.repository

import com.example.tracker.domain.models.PlayerInfoEntity
import com.example.tracker.domain.models.PlayerSeasonInfoEntity
import com.example.tracker.domain.models.SeasonInfoEntity

interface TrackerRepository {

    suspend fun getPlayer(playerName: String): PlayerInfoEntity

    suspend fun getSeasons(): List<SeasonInfoEntity>

    suspend fun getCurrentSeason(): SeasonInfoEntity

    suspend fun getPlayerSeasonInfo(playerName: String, season: String): PlayerSeasonInfoEntity
}