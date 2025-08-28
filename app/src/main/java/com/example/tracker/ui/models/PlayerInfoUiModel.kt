package com.example.tracker.ui.models

import android.os.Parcelable
import com.example.tracker.data.network.models.player_name_response.BanType
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlayerInfoUiModel(
    val id: String,
    val banType: BanType,
    val clanId: String,
    val name: String
) : Parcelable
