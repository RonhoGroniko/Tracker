package com.example.tracker.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.tracker.data.db.models.SeasonDbModel


@Dao
interface SeasonDao {

    @Insert(onConflict = REPLACE)
    suspend fun addSeason(season: SeasonDbModel)

    @Query("SELECT * FROM seasons")
    suspend fun getSeasonList(): List<SeasonDbModel>
}
