package com.example.tracker.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.tracker.data.db.models.PlayerStatsDbModel

@Dao
interface PlayerStatsDao {

    @Insert(onConflict = REPLACE)
    suspend fun addPlayerStats(playerStats: PlayerStatsDbModel)

    @Query("SELECT * FROM player_stats WHERE playerId= :playerId AND seasonId= :seasonId LIMIT 1")
    suspend fun getPlayerStats(playerId: String, seasonId: String) : PlayerStatsDbModel?
}