package com.example.tracker.data.network.models.player_name_response

import com.google.gson.annotations.SerializedName

data class PlayerDataDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("attributes")
    val attributes: PlayerAttributesDto
)
