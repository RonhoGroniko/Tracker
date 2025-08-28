package com.example.tracker.data.network.models.player_name_response

import com.google.gson.annotations.SerializedName

data class PlayerAttributesDto(
    @SerializedName("banType")
    val banType: BanType,
    @SerializedName("clanId")
    val clanId: String,
    @SerializedName("name")
    val name: String
)
