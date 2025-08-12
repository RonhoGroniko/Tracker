package com.example.tracker.data.network.models.season

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeasonDataDto(
    @SerialName("id")
    val id: String,
    @SerialName("attributes")
    val attributes: SeasonAttributesDto
)
