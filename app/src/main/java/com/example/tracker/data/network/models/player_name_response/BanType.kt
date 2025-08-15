package com.example.tracker.data.network.models.player_name_response

import com.google.gson.annotations.SerializedName

enum class BanType {
    @SerializedName("Innocent")
    INNOCENT,

    @SerializedName("TemporaryBan")
    TEMPORARY_BAN,

    @SerializedName("PermanentBan")
    PERMANENT_BAN
}