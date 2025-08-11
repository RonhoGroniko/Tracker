package com.example.tracker.domain.repository

import com.example.tracker.domain.models.PlayerInfoEntity
import com.example.tracker.domain.models.SeasonInfoEntity

interface TrackerRepository {

    suspend fun getPlayer(playerName: String): PlayerInfoEntity

    suspend fun getSeasons(): List<SeasonInfoEntity>
}