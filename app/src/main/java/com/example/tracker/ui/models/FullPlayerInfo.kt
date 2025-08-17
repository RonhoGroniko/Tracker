package com.example.tracker.ui.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FullPlayerInfo(
    val name: String,
    val stats: PlayerSeasonGameModeStatsUiModel
) : Parcelable
