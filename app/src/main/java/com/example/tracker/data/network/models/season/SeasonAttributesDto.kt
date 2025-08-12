package com.example.tracker.data.network.models.season

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeasonAttributesDto(
    @SerialName("isCurrentSeason")
    val isCurrentSeason: Boolean
)
