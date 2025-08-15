package com.example.tracker.data.mappers

import com.example.tracker.data.network.models.player_season_response.PlayerSeasonGameModeDto
import com.example.tracker.data.network.models.player_season_response.PlayerSeasonGameModeStatsDto
import com.example.tracker.data.network.models.player_season_response.PlayerSeasonGameStatsDto
import com.example.tracker.domain.models.PlayerSeasonGameModeEntity
import com.example.tracker.domain.models.PlayerSeasonGameModeStatsEntity
import com.example.tracker.domain.models.PlayerSeasonGameStatsEntity
import com.example.tracker.ui.models.PlayerSeasonGameModeStatsUiModel
import com.example.tracker.ui.models.PlayerSeasonGameModeUiModel
import com.example.tracker.ui.models.PlayerSeasonGameStatsUiModel

fun PlayerSeasonGameModeStatsDto.toPlayerSeasonGameModeStatsEntity() =
    PlayerSeasonGameModeStatsEntity(
        gameModeStats = gameModeStats.toPlayerSeasonGameModeEntity()
    )

fun PlayerSeasonGameModeDto.toPlayerSeasonGameModeEntity() = PlayerSeasonGameModeEntity(
    duo = duo.toPlayerSeasonGameStatsEntity(),
    duoFpp = duoFpp.toPlayerSeasonGameStatsEntity(),
    solo = solo.toPlayerSeasonGameStatsEntity(),
    soloFpp = soloFpp.toPlayerSeasonGameStatsEntity(),
    squad = squad.toPlayerSeasonGameStatsEntity(),
    squadFpp = squadFpp.toPlayerSeasonGameStatsEntity()
)

fun PlayerSeasonGameStatsDto.toPlayerSeasonGameStatsEntity() = PlayerSeasonGameStatsEntity(
    assists = assists,
    knocked = knocked,
    damageDealt = damageDealt,
    headshotKills = headshotKills,
    kills = kills,
    longestKill = longestKill,
    longestTimeSurvived = longestTimeSurvived,
    mostSurvivalTime = mostSurvivalTime,
    revives = revives,
    rideDistance = rideDistance,
    roadKills = roadKills,
    roundMostKills = roundMostKills,
    roundsPlayed = roundsPlayed,
    swimDistance = swimDistance,
    timeSurvived = timeSurvived,
    top10s = top10s,
    vehicleDestroys = vehicleDestroys,
    walkDistance = walkDistance,
    wins = wins
)


fun PlayerSeasonGameModeStatsEntity.toPlayerSeasonGameModeStatsUiModel() = PlayerSeasonGameModeStatsUiModel(
    gameModeStats = gameModeStats.toPlayerSeasonGameModeUiModel()
)


fun PlayerSeasonGameModeEntity.toPlayerSeasonGameModeUiModel() = PlayerSeasonGameModeUiModel(
    duo = duo.toPlayerSeasonInfoUiModel(),
    duoFpp = duoFpp.toPlayerSeasonInfoUiModel(),
    solo = solo.toPlayerSeasonInfoUiModel(),
    soloFpp = soloFpp.toPlayerSeasonInfoUiModel(),
    squad = squad.toPlayerSeasonInfoUiModel(),
    squadFpp = squadFpp.toPlayerSeasonInfoUiModel()
)

fun PlayerSeasonGameStatsEntity.toPlayerSeasonInfoUiModel() = PlayerSeasonGameStatsUiModel(
    assists = assists,
    knocked = knocked,
    damageDealt = damageDealt,
    headshotKills = headshotKills,
    kills = kills,
    longestKill = longestKill,
    longestTimeSurvived = longestTimeSurvived,
    mostSurvivalTime = mostSurvivalTime,
    revives = revives,
    rideDistance = rideDistance,
    roadKills = roadKills,
    roundMostKills = roundMostKills,
    roundsPlayed = roundsPlayed,
    swimDistance = swimDistance,
    timeSurvived = timeSurvived,
    top10s = top10s,
    vehicleDestroys = vehicleDestroys,
    walkDistance = walkDistance,
    wins = wins
)
