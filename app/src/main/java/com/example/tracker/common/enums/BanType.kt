package com.example.tracker.common.enums

import com.google.gson.annotations.SerializedName

enum class BanType {
    @SerializedName("Innocent")
    INNOCENT,

    @SerializedName("TemporaryBan")
    TEMPORARY_BAN,

    @SerializedName("PermanentBan")
    PERMANENT_BAN
}