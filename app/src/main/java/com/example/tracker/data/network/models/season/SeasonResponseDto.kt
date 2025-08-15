package com.example.tracker.data.network.models.season

import com.google.gson.annotations.SerializedName

data class SeasonResponseDto(
    @SerializedName("data")
    val data: List<SeasonDataDto>
)
