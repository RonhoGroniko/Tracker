package com.example.tracker.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import com.example.tracker.data.db.models.SeasonDbModel


@Dao
interface SeasonDao {

    @Insert(onConflict = REPLACE)
    suspend fun addSeason(season: SeasonDbModel)
}
