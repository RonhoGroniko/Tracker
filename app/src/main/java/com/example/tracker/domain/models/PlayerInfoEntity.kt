package com.example.tracker.domain.models

import com.example.tracker.common.enums.BanType

data class PlayerInfoEntity(
    val banType: BanType,
    val clanId: String,
    val name: String
)
