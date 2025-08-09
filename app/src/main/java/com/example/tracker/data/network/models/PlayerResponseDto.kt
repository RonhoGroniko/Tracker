package com.example.tracker.data.network.models

import com.google.gson.annotations.SerializedName

data class PlayerResponseDto(
    @SerializedName("data")
    val data: List<PlayerDataDto>
)