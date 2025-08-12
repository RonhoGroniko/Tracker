package com.example.tracker.data.network.models.player_name_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerResponseDto(
    @SerialName("data")
    val data: List<PlayerDataDto>
)