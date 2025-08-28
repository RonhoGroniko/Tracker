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

    @Query("SELECT * FROM Seasons WHERE isCurrentSeason = 1 LIMIT 1")
    suspend fun getCurrentSeason() : SeasonDbModel

    @Query("SELECT * FROM seasons WHERE name = :seasonName")
    suspend fun getSeasonByName(seasonName: String): SeasonDbModel
}
