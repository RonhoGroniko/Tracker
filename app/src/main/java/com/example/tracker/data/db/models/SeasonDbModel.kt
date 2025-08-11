package com.example.tracker.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Seasons")
data class SeasonDbModel(
    @PrimaryKey()
    val id: String,
    val isCurrentSeason: Boolean
)
