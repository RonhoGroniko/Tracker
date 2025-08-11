package com.example.tracker.data.network.models.player_season_response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed class GameMode

@Serializable
data class Duo(
    @SerialName("assists")
    val assists: Int,
    @SerialName("dBNOs")
    val knocked: Int, // Number of enemy players knocked
    @SerialName("damageDealt")
    val damageDealt: Int,
    @SerialName("headshotKills")
    val headshotKills: Int,
    @SerialName("kills")
    val kills: Int,
    @SerialName("longestKill")
    val longestKill: Int,
    @SerialName("longestTimeSurvived")
    val longestTimeSurvived: Int,
    @SerialName("mostSurvivalTime")
    val mostSurvivalTime: Int,
    @SerialName("revives")
    val revives: Int,
    @SerialName("rideDistance")
    val rideDistance: Int,
    @SerialName("roadKills")
    val roadKills: Int,
    @SerialName("roundMostKills")
    val roundMostKills: Int,
    @SerialName("roundsPlayed")
    val roundsPlayed: Int,
    @SerialName("swimDistance")
    val swimDistance: Int,
    @SerialName("timeSurvived")
    val timeSurvived: Int,
    @SerialName("top10s")
    val top10s: Int,
    @SerialName("vehicleDestroys")
    val vehicleDestroys: Int,
    @SerialName("walkDistance")
    val walkDistance: Int,
    @SerialName("wins")
    val wins: Int
) : GameMode()

@Serializable
data class DuoFpp(
    @SerialName("assists")
    val assists: Int,
    @SerialName("dBNOs")
    val knocked: Int,
    @SerialName("damageDealt")
    val damageDealt: Int,
    @SerialName("headshotKills")
    val headshotKills: Int,
    @SerialName("kills")
    val kills: Int,
    @SerialName("longestKill")
    val longestKill: Int,
    @SerialName("longestTimeSurvived")
    val longestTimeSurvived: Int,
    @SerialName("mostSurvivalTime")
    val mostSurvivalTime: Int,
    @SerialName("revives")
    val revives: Int,
    @SerialName("rideDistance")
    val rideDistance: Int,
    @SerialName("roadKills")
    val roadKills: Int,
    @SerialName("roundMostKills")
    val roundMostKills: Int,
    @SerialName("roundsPlayed")
    val roundsPlayed: Int,
    @SerialName("swimDistance")
    val swimDistance: Int,
    @SerialName("timeSurvived")
    val timeSurvived: Int,
    @SerialName("top10s")
    val top10s: Int,
    @SerialName("vehicleDestroys")
    val vehicleDestroys: Int,
    @SerialName("walkDistance")
    val walkDistance: Int,
    @SerialName("wins")
    val wins: Int
): GameMode()

@Serializable
data class Solo(
    @SerialName("assists")
    val assists: Int,
    @SerialName("dBNOs")
    val knocked: Int,
    @SerialName("damageDealt")
    val damageDealt: Int,
    @SerialName("headshotKills")
    val headshotKills: Int,
    @SerialName("kills")
    val kills: Int,
    @SerialName("longestKill")
    val longestKill: Int,
    @SerialName("longestTimeSurvived")
    val longestTimeSurvived: Int,
    @SerialName("mostSurvivalTime")
    val mostSurvivalTime: Int,
    @SerialName("revives")
    val revives: Int,
    @SerialName("rideDistance")
    val rideDistance: Int,
    @SerialName("roadKills")
    val roadKills: Int,
    @SerialName("roundMostKills")
    val roundMostKills: Int,
    @SerialName("roundsPlayed")
    val roundsPlayed: Int,
    @SerialName("swimDistance")
    val swimDistance: Int,
    @SerialName("timeSurvived")
    val timeSurvived: Int,
    @SerialName("top10s")
    val top10s: Int,
    @SerialName("vehicleDestroys")
    val vehicleDestroys: Int,
    @SerialName("walkDistance")
    val walkDistance: Int,
    @SerialName("wins")
    val wins: Int
): GameMode()

@Serializable
data class SoloFpp(
    @SerialName("assists")
    val assists: Int,
    @SerialName("dBNOs")
    val knocked: Int,
    @SerialName("damageDealt")
    val damageDealt: Int,
    @SerialName("headshotKills")
    val headshotKills: Int,
    @SerialName("kills")
    val kills: Int,
    @SerialName("longestKill")
    val longestKill: Int,
    @SerialName("longestTimeSurvived")
    val longestTimeSurvived: Int,
    @SerialName("mostSurvivalTime")
    val mostSurvivalTime: Int,
    @SerialName("revives")
    val revives: Int,
    @SerialName("rideDistance")
    val rideDistance: Int,
    @SerialName("roadKills")
    val roadKills: Int,
    @SerialName("roundMostKills")
    val roundMostKills: Int,
    @SerialName("roundsPlayed")
    val roundsPlayed: Int,
    @SerialName("swimDistance")
    val swimDistance: Int,
    @SerialName("timeSurvived")
    val timeSurvived: Int,
    @SerialName("top10s")
    val top10s: Int,
    @SerialName("vehicleDestroys")
    val vehicleDestroys: Int,
    @SerialName("walkDistance")
    val walkDistance: Int,
    @SerialName("wins")
    val wins: Int
): GameMode()

@Serializable
data class Squad(
    @SerialName("assists")
    val assists: Int,
    @SerialName("dBNOs")
    val knocked: Int,
    @SerialName("damageDealt")
    val damageDealt: Int,
    @SerialName("headshotKills")
    val headshotKills: Int,
    @SerialName("kills")
    val kills: Int,
    @SerialName("longestKill")
    val longestKill: Int,
    @SerialName("longestTimeSurvived")
    val longestTimeSurvived: Int,
    @SerialName("mostSurvivalTime")
    val mostSurvivalTime: Int,
    @SerialName("revives")
    val revives: Int,
    @SerialName("rideDistance")
    val rideDistance: Int,
    @SerialName("roadKills")
    val roadKills: Int,
    @SerialName("roundMostKills")
    val roundMostKills: Int,
    @SerialName("roundsPlayed")
    val roundsPlayed: Int,
    @SerialName("swimDistance")
    val swimDistance: Int,
    @SerialName("timeSurvived")
    val timeSurvived: Int,
    @SerialName("top10s")
    val top10s: Int,
    @SerialName("vehicleDestroys")
    val vehicleDestroys: Int,
    @SerialName("walkDistance")
    val walkDistance: Int,
    @SerialName("wins")
    val wins: Int
): GameMode()

@Serializable
data class SquadFpp(
    @SerialName("assists")
    val assists: Int,
    @SerialName("dBNOs")
    val knocked: Int,
    @SerialName("damageDealt")
    val damageDealt: Int,
    @SerialName("headshotKills")
    val headshotKills: Int,
    @SerialName("kills")
    val kills: Int,
    @SerialName("longestKill")
    val longestKill: Int,
    @SerialName("longestTimeSurvived")
    val longestTimeSurvived: Int,
    @SerialName("mostSurvivalTime")
    val mostSurvivalTime: Int,
    @SerialName("revives")
    val revives: Int,
    @SerialName("rideDistance")
    val rideDistance: Int,
    @SerialName("roadKills")
    val roadKills: Int,
    @SerialName("roundMostKills")
    val roundMostKills: Int,
    @SerialName("roundsPlayed")
    val roundsPlayed: Int,
    @SerialName("swimDistance")
    val swimDistance: Int,
    @SerialName("timeSurvived")
    val timeSurvived: Int,
    @SerialName("top10s")
    val top10s: Int,
    @SerialName("vehicleDestroys")
    val vehicleDestroys: Int,
    @SerialName("walkDistance")
    val walkDistance: Int,
    @SerialName("wins")
    val wins: Int
): GameMode()
