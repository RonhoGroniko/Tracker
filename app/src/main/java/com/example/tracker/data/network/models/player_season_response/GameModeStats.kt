package com.example.tracker.data.network.models.player_season_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameModeStats(
    @SerialName("duo")
    val duo: Duo,
    @SerialName("duo-fpp")
    val duoFpp: DuoFpp,
    @SerialName("solo")
    val solo: Solo,
    @SerialName("solo-fpp")
    val soloFpp: SoloFpp,
    @SerialName("squad")
    val squad: Squad,
    @SerialName("squad-fpp")
    val squadFpp: SquadFpp
)