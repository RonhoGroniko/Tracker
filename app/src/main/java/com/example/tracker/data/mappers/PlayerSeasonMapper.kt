package com.example.tracker.data.mappers

import com.example.tracker.R
import com.example.tracker.data.network.models.player_season_response.PlayerSeasonGameModeDto
import com.example.tracker.data.network.models.player_season_response.PlayerSeasonGameModeStatsDto
import com.example.tracker.data.network.models.player_season_response.PlayerSeasonGameStatsDto
import com.example.tracker.domain.models.PlayerSeasonGameModeEntity
import com.example.tracker.domain.models.PlayerSeasonGameModeStatsEntity
import com.example.tracker.domain.models.PlayerSeasonGameStatsEntity
import com.example.tracker.ui.models.PlayerSeasonGameModeStatsUiModel
import com.example.tracker.ui.models.PlayerSeasonGameModeUiModel
import com.example.tracker.ui.models.PlayerSeasonGameStatsUiModel
import com.example.tracker.ui.models.StatItemUiModel

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


// TODO: CHANGE DRAWABLES
fun PlayerSeasonGameStatsUiModel.toStatItems(): List<StatItemUiModel> {
    return listOf(
        StatItemUiModel(R.drawable.ic_helmet, "Wins", wins.toString()),
        StatItemUiModel(R.drawable.ic_helmet, "Kills", kills.toString()),
        StatItemUiModel(R.drawable.ic_helmet, "Assists", assists.toString()),
        StatItemUiModel(R.drawable.ic_helmet, "Knocked", knocked.toString()),
        StatItemUiModel(R.drawable.ic_helmet, "Damage Dealt", "%.1f".format(damageDealt)),
        StatItemUiModel(R.drawable.ic_helmet, "Headshot Kills", headshotKills.toString()),
        StatItemUiModel(R.drawable.ic_helmet, "Longest Kill", "%.1f m".format(longestKill)),
        StatItemUiModel(R.drawable.ic_helmet, "Longest Time Survived", "%.1f min".format(longestTimeSurvived / 60)),
        StatItemUiModel(R.drawable.ic_helmet, "Most Survival Time", "%.1f min".format(mostSurvivalTime / 60)),
        StatItemUiModel(R.drawable.ic_helmet, "Revives", revives.toString()),
        StatItemUiModel(R.drawable.ic_helmet, "Ride Distance", "%.1f km".format(rideDistance / 1000)),
        StatItemUiModel(R.drawable.ic_helmet, "Road Kills", roadKills.toString()),
        StatItemUiModel(R.drawable.ic_helmet, "Round Most Kills", roundMostKills.toString()),
        StatItemUiModel(R.drawable.ic_helmet, "Rounds Played", roundsPlayed.toString()),
        StatItemUiModel(R.drawable.ic_helmet, "Swim Distance", "%.1f m".format(swimDistance)),
        StatItemUiModel(R.drawable.ic_helmet, "Time Survived", "%.1f min".format(timeSurvived / 60)),
        StatItemUiModel(R.drawable.ic_helmet, "Top 10s", top10s.toString()),
        StatItemUiModel(R.drawable.ic_helmet, "Vehicle Destroys", vehicleDestroys.toString()),
        StatItemUiModel(R.drawable.ic_helmet, "Walk Distance", "%.1f km".format(walkDistance / 1000))
    )
}
