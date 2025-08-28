package com.example.tracker.ui.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlayerSeasonGameModeStatsUiModel(
    val gameModeStats: PlayerSeasonGameModeUiModel
) : Parcelable

@Parcelize
data class PlayerSeasonGameModeUiModel(
    val duo: PlayerSeasonGameStatsUiModel,
    val duoFpp: PlayerSeasonGameStatsUiModel,
    val solo: PlayerSeasonGameStatsUiModel,
    val soloFpp: PlayerSeasonGameStatsUiModel,
    val squad: PlayerSeasonGameStatsUiModel,
    val squadFpp: PlayerSeasonGameStatsUiModel,
) : Parcelable

@Parcelize
data class PlayerSeasonGameStatsUiModel (
    val assists: Int,
    val knocked: Int, // Number of enemy players knocked
    val damageDealt: Double,
    val headshotKills: Int,
    val kills: Int,
    val longestKill: Double,
    val longestTimeSurvived: Double,
    val mostSurvivalTime: Double,
    val revives: Int,
    val rideDistance: Double,
    val roadKills: Int,
    val roundMostKills: Int,
    val roundsPlayed: Int,
    val swimDistance: Double,
    val timeSurvived: Double,
    val top10s: Int,
    val vehicleDestroys: Int,
    val walkDistance: Double,
    val wins: Int
) : Parcelable
