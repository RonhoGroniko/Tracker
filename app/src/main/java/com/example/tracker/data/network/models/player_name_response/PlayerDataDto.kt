package com.example.tracker.data.network.models.player_name_response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerDataDto(
    @SerialName("id")
    val id: String,
    @SerialName("attributes")
    val attributes: PlayerAttributesDto
)
