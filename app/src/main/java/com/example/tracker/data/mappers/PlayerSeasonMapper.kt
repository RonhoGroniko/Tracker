package com.example.tracker.data.mappers

import com.example.tracker.R
import com.example.tracker.data.db.models.PlayerStatsDbModel
import com.example.tracker.data.db.models.PlayerStatsGameModeDbModel
import com.example.tracker.data.db.models.PlayerStatsGameStatsDbModel
import com.example.tracker.data.network.models.player_season_response.PlayerSeasonGameModeDto
import com.example.tracker.data.network.models.player_season_response.PlayerSeasonGameModeStatsDto
import com.example.tracker.data.network.models.player_season_response.PlayerSeasonGameStatsDto
import com.example.tracker.domain.models.PlayerInfoEntity
import com.example.tracker.domain.models.PlayerSeasonGameModeEntity
import com.example.tracker.domain.models.PlayerSeasonGameModeStatsEntity
import com.example.tracker.domain.models.PlayerSeasonGameStatsEntity
import com.example.tracker.ui.models.PlayerInfoUiModel
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

fun  PlayerSeasonGameModeStatsEntity.toPlayerStatsDbModel(playerId: String, seasonId: String) = PlayerStatsDbModel(
    playerId = playerId,
    seasonId = seasonId,
    gameModeStats = gameModeStats.toPlayerStatsGameModeDbModel()
)

fun PlayerSeasonGameModeEntity.toPlayerStatsGameModeDbModel() = PlayerStatsGameModeDbModel(
    duo = duo.toPlayerStatsGameStatsDbModel(),
    duoFpp = duoFpp.toPlayerStatsGameStatsDbModel(),
    solo = solo.toPlayerStatsGameStatsDbModel(),
    soloFpp = soloFpp.toPlayerStatsGameStatsDbModel(),
    squad = squad.toPlayerStatsGameStatsDbModel(),
    squadFpp = squadFpp.toPlayerStatsGameStatsDbModel()
)

fun PlayerSeasonGameStatsEntity.toPlayerStatsGameStatsDbModel() = PlayerStatsGameStatsDbModel(
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

fun PlayerStatsDbModel.toPlayerSeasonGameModeStatsUiModel() = PlayerSeasonGameModeStatsUiModel(
    gameModeStats = gameModeStats.toPlayerSeasonGameModeUiModel()
)

fun PlayerStatsGameModeDbModel.toPlayerSeasonGameModeUiModel() = PlayerSeasonGameModeUiModel(
    duo = duo.toPlayerSeasonGameStatsUiModel(),
    duoFpp = duoFpp.toPlayerSeasonGameStatsUiModel(),
    solo = solo.toPlayerSeasonGameStatsUiModel(),
    soloFpp = soloFpp.toPlayerSeasonGameStatsUiModel(),
    squad = squad.toPlayerSeasonGameStatsUiModel(),
    squadFpp = squadFpp.toPlayerSeasonGameStatsUiModel()
)

fun PlayerStatsGameStatsDbModel.toPlayerSeasonGameStatsUiModel() = PlayerSeasonGameStatsUiModel(
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

fun PlayerInfoEntity.toPlayerInfoUiModel() = PlayerInfoUiModel(
    id = id,
    banType = banType,
    clanId = clanId,
    name = name
)

fun PlayerStatsDbModel.toPlayerSeasonGameModeStatsEntity() = PlayerSeasonGameModeStatsEntity(
    gameModeStats = gameModeStats.toPlayerSeasonGameModeEntity()
)

fun PlayerStatsGameModeDbModel.toPlayerSeasonGameModeEntity() = PlayerSeasonGameModeEntity(
    duo = duo.toPlayerSeasonGameStatsEntity(),
    duoFpp = duoFpp.toPlayerSeasonGameStatsEntity(),
    solo = solo.toPlayerSeasonGameStatsEntity(),
    soloFpp = soloFpp.toPlayerSeasonGameStatsEntity(),
    squad = squad.toPlayerSeasonGameStatsEntity(),
    squadFpp = squadFpp.toPlayerSeasonGameStatsEntity()
)

fun PlayerStatsGameStatsDbModel.toPlayerSeasonGameStatsEntity() = PlayerSeasonGameStatsEntity(
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

fun PlayerSeasonGameStatsUiModel.toStatItems(): List<StatItemUiModel> {
    return listOf(
        StatItemUiModel(R.drawable.ic_trophy, "Wins", wins.toString()),
        StatItemUiModel(R.drawable.ic_skull, "Kills", kills.toString()),
        StatItemUiModel(R.drawable.ic_handshake, "Assists", assists.toString()),
        StatItemUiModel(R.drawable.ic_crawling, "Knocked", knocked.toString()),
        StatItemUiModel(R.drawable.ic_dmg, "Damage Dealt", "%.1f".format(damageDealt)),
        StatItemUiModel(R.drawable.ic_headshot, "Headshot Kills", headshotKills.toString()),
        StatItemUiModel(R.drawable.ic_sniper_scope, "Longest Kill", "%.1f m".format(longestKill)),
        StatItemUiModel(R.drawable.ic_sand_timer, "Longest Time Survived", "%.1f min".format(longestTimeSurvived / 60)),
        StatItemUiModel(R.drawable.ic_sand_timer, "Most Survival Time", "%.1f min".format(mostSurvivalTime / 60)),
        StatItemUiModel(R.drawable.ic_revive, "Revives", revives.toString()),
        StatItemUiModel(R.drawable.ic_car, "Ride Distance", "%.1f km".format(rideDistance / 1000)),
        StatItemUiModel(R.drawable.ic_car_windscreen, "Road Kills", roadKills.toString()),
        StatItemUiModel(R.drawable.ic_skull, "Round Most Kills", roundMostKills.toString()),
        StatItemUiModel(R.drawable.ic_swim, "Swim Distance", "%.1f m".format(swimDistance)),
        StatItemUiModel(R.drawable.ic_sand_timer, "Time Survived", "%.1f min".format(timeSurvived / 60)),
        StatItemUiModel(R.drawable.ic_top_10, "Top 10s", top10s.toString()),
        StatItemUiModel(R.drawable.ic_car_broken, "Vehicle Destroys", vehicleDestroys.toString()),
        StatItemUiModel(R.drawable.ic_footprint, "Walk Distance", "%.1f km".format(walkDistance / 1000))
    )
}
