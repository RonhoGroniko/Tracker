package com.example.tracker.domain.models

import com.example.tracker.data.network.models.player_name_response.BanType

data class PlayerInfoEntity(
    val id: String,
    val banType: BanType,
    val clanId: String,
    val name: String
)
