package com.example.tracker.data.network.models.player_name_response

import com.google.gson.annotations.SerializedName

data class PlayerResponseDto(
    @SerializedName("data")
    val data: List<PlayerDataDto>
)