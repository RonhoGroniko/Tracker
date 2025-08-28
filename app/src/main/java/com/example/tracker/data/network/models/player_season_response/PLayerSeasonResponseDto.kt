package com.example.tracker.data.network.models.player_season_response

import com.google.gson.annotations.SerializedName

data class PLayerSeasonResponseDto(
    @SerializedName("data")
    val data: PlayerSeasonAttributesDto
)

data class PlayerSeasonAttributesDto(
    @SerializedName("attributes")
    val attributes: PlayerSeasonGameModeStatsDto
)

data class PlayerSeasonGameModeStatsDto(
    @SerializedName("gameModeStats")
    val gameModeStats: PlayerSeasonGameModeDto
)

data class PlayerSeasonGameModeDto(
    @SerializedName("duo")
    val duo: PlayerSeasonGameStatsDto,
    @SerializedName("duo-fpp")
    val duoFpp: PlayerSeasonGameStatsDto,
    @SerializedName("solo")
    val solo: PlayerSeasonGameStatsDto,
    @SerializedName("solo-fpp")
    val soloFpp: PlayerSeasonGameStatsDto,
    @SerializedName("squad")
    val squad: PlayerSeasonGameStatsDto,
    @SerializedName("squad-fpp")
    val squadFpp: PlayerSeasonGameStatsDto,
)

data class PlayerSeasonGameStatsDto(
    @SerializedName("assists")
    val assists: Int,
    @SerializedName("dBNOs")
    val knocked: Int,
    @SerializedName("damageDealt")
    val damageDealt: Double,
    @SerializedName("headshotKills")
    val headshotKills: Int,
    @SerializedName("kills")
    val kills: Int,
    @SerializedName("longestKill")
    val longestKill: Double,
    @SerializedName("longestTimeSurvived")
    val longestTimeSurvived: Double,
    @SerializedName("mostSurvivalTime")
    val mostSurvivalTime: Double,
    @SerializedName("revives")
    val revives: Int,
    @SerializedName("rideDistance")
    val rideDistance: Double,
    @SerializedName("roadKills")
    val roadKills: Int,
    @SerializedName("roundMostKills")
    val roundMostKills: Int,
    @SerializedName("roundsPlayed")
    val roundsPlayed: Int,
    @SerializedName("swimDistance")
    val swimDistance: Double,
    @SerializedName("timeSurvived")
    val timeSurvived: Double,
    @SerializedName("top10s")
    val top10s: Int,
    @SerializedName("vehicleDestroys")
    val vehicleDestroys: Int,
    @SerializedName("walkDistance")
    val walkDistance: Double,
    @SerializedName("wins")
    val wins: Int
)
