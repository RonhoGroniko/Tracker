package com.example.tracker.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tracker.data.network.models.player_name_response.BanType

@Entity("players")
data class PlayerDbModel(
    @PrimaryKey()
    val playerId: String,
    val banType: BanType,
    val clanId: String,
    val name: String
)
