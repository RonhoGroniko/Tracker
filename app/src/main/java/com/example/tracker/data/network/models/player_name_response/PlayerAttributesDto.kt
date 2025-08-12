package com.example.tracker.data.network.models.player_name_response

import com.example.tracker.common.enums.BanType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerAttributesDto(
    @SerialName("banType")
    val banType: BanType,
    @SerialName("clanId")
    val clanId: String,
    @SerialName("name")
    val name: String
)
