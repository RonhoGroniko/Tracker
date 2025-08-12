package com.example.tracker.data.mappers

import com.example.tracker.data.network.models.player_season_response.GameMode
import com.example.tracker.data.network.models.player_season_response.PLayerSeasonResponseDto
import com.example.tracker.domain.models.PlayerSeasonInfoEntity
import com.example.tracker.ui.models.PlayerSeasonInfoUiModel


//TODO: store all data in entity
fun PLayerSeasonResponseDto.toPlayerSeasonInfoEntity(gameMode: GameMode): PlayerSeasonInfoEntity {
    val stats = when(gameMode) {
        GameMode.DUO -> data.attributes.gameModeStats.duo
        GameMode.DUO_FPP -> data.attributes.gameModeStats.duoFpp
        GameMode.SOLO -> data.attributes.gameModeStats.solo
        GameMode.SOLO_FPP -> data.attributes.gameModeStats.soloFpp
        GameMode.SQUAD -> data.attributes.gameModeStats.squad
        GameMode.SQUAD_FPP -> data.attributes.gameModeStats.squadFpp
    }
    return PlayerSeasonInfoEntity(
        assists = stats.assists,
        knocked = stats.knocked,
        damageDealt = stats.damageDealt,
        headshotKills = stats.headshotKills,
        kills = stats.kills,
        longestKill = stats.longestKill,
        longestTimeSurvived = stats.longestTimeSurvived,
        mostSurvivalTime = stats.mostSurvivalTime,
        revives = stats.revives,
        rideDistance = stats.rideDistance,
        roadKills = stats.roadKills,
        roundMostKills = stats.roundMostKills,
        roundsPlayed = stats.roundsPlayed,
        swimDistance = stats.swimDistance,
        timeSurvived = stats.timeSurvived,
        top10s = stats.top10s,
        vehicleDestroys = stats.vehicleDestroys,
        walkDistance = stats.walkDistance,
        wins = stats.wins
    )
}

fun PlayerSeasonInfoEntity.toPlayerSeasonInfoUiModel() = PlayerSeasonInfoUiModel(
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
