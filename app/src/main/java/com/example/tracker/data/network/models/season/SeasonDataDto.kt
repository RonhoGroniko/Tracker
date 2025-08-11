package com.example.tracker.data.network.models.season

import com.google.gson.annotations.SerializedName

data class SeasonDataDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("attributes")
    val attributes: SeasonAttributesDto
)
