package com.example.tracker.data.db.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "player_stats",
    primaryKeys = ["playerId", "seasonId"],
    foreignKeys = [
        ForeignKey(
            entity = PlayerDbModel::class,
            parentColumns = ["playerId"],
            childColumns = ["playerId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = SeasonDbModel::class,
            parentColumns = ["seasonId"],
            childColumns = ["seasonId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PlayerStatsDbModel(
    val playerId: String,
    val seasonId: String,

    @Embedded(prefix = "gm_")
    val gameModeStats: PlayerStatsGameModeDbModel
)

data class PlayerStatsGameModeDbModel(
    @Embedded(prefix = "duo_")
    val duo: PlayerStatsGameStatsDbModel,

    @Embedded(prefix = "duoFpp_")
    val duoFpp: PlayerStatsGameStatsDbModel,

    @Embedded(prefix = "solo_")
    val solo: PlayerStatsGameStatsDbModel,

    @Embedded(prefix = "soloFpp_")
    val soloFpp: PlayerStatsGameStatsDbModel,

    @Embedded(prefix = "squad_")
    val squad: PlayerStatsGameStatsDbModel,

    @Embedded(prefix = "squadFpp_")
    val squadFpp: PlayerStatsGameStatsDbModel
)

data class PlayerStatsGameStatsDbModel(
    val assists: Int,
    val knocked: Int,
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

// TODO: ЗАГРУЖАЕМ ОДИН РАЗ ВСЕ ЕСЛИ ПУСТОЙ, ЕСЛИ НЕ ПУСТОЙ, ТО ГРУЗИМ ТОЛЬКО КАРРЕНТ СИЗОН