package com.example.tracker.data.network.models.player_season_response

import com.google.gson.annotations.SerializedName

data class PlayerSeasonResponseDto(
    @SerializedName("data")
    val data: PlayerSeasonAttributesDto
)