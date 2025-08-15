package com.example.tracker.data.network.models.season

import com.google.gson.annotations.SerializedName

data class SeasonAttributesDto(
    @SerializedName("isCurrentSeason")
    val isCurrentSeason: Boolean
)
