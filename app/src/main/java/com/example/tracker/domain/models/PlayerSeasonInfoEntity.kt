package com.example.tracker.domain.models


data class PlayerSeasonInfoEntity(
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
)
