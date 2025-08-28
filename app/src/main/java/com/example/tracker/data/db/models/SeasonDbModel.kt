package com.example.tracker.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Seasons")
data class SeasonDbModel(
    @PrimaryKey()
    val seasonId: String,
    val name: String,
    val isCurrentSeason: Boolean
)
