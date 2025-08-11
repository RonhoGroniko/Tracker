package com.example.tracker.domain.repository

import com.example.tracker.domain.models.PlayerInfoEntity

interface TrackerRepository {

    suspend fun getPlayer(playerName: String): PlayerInfoEntity
}