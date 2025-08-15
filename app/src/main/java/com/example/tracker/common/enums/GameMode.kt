package com.example.tracker.common.enums

import kotlinx.serialization.Serializable

@Serializable
enum class GameMode {
    DUO, DUO_FPP, SOLO, SOLO_FPP, SQUAD, SQUAD_FPP
}