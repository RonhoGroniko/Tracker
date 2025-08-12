package com.example.tracker.data.network.models.player_season_response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PLayerSeasonResponseDto(
    @SerialName("data")
    val data: PlayerSeasonAttributesDto
)

@Serializable
data class PlayerSeasonAttributesDto(
    @SerialName("attributes")
    val attributes: PlayerSeasonGameModeStats
)

@Serializable
data class PlayerSeasonGameModeStats(
    @SerialName("gameModeStats")
    val gameModeStats: PlayerSeasonGameMode
)

@Serializable
data class PlayerSeasonGameMode(
    @SerialName("duo")
    val duo: PlayerSeasonGameStats,
    @SerializedName("duo-fpp")
    val duoFpp: PlayerSeasonGameStats,
    @SerialName("solo")
    val solo: PlayerSeasonGameStats,
    @SerialName("solo-fpp")
    val soloFpp: PlayerSeasonGameStats,
    @SerialName("squad")
    val squad: PlayerSeasonGameStats,
    @SerialName("squad-fpp")
    val squadFpp: PlayerSeasonGameStats,
)

@Serializable
data class PlayerSeasonGameStats(
    @SerialName("assists")
    val assists: Int,
    @SerialName("dBNOs")
    val knocked: Int,
    @SerialName("damageDealt")
    val damageDealt: Double,
    @SerialName("headshotKills")
    val headshotKills: Int,
    @SerialName("kills")
    val kills: Int,
    @SerialName("longestKill")
    val longestKill: Double,
    @SerialName("longestTimeSurvived")
    val longestTimeSurvived: Double,
    @SerialName("mostSurvivalTime")
    val mostSurvivalTime: Double,
    @SerialName("revives")
    val revives: Int,
    @SerialName("rideDistance")
    val rideDistance: Double,
    @SerialName("roadKills")
    val roadKills: Int,
    @SerialName("roundMostKills")
    val roundMostKills: Int,
    @SerialName("roundsPlayed")
    val roundsPlayed: Int,
    @SerialName("swimDistance")
    val swimDistance: Double,
    @SerialName("timeSurvived")
    val timeSurvived: Double,
    @SerialName("top10s")
    val top10s: Int,
    @SerialName("vehicleDestroys")
    val vehicleDestroys: Int,
    @SerialName("walkDistance")
    val walkDistance: Double,
    @SerialName("wins")
    val wins: Int
)

@Serializable
enum class GameMode {
    DUO, DUO_FPP, SOLO, SOLO_FPP, SQUAD, SQUAD_FPP
}