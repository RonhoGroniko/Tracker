package com.example.tracker.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import com.example.tracker.data.db.models.PlayerDbModel

@Dao
interface PlayerDao {

    @Insert(onConflict = IGNORE)
    suspend fun addPlayer(player: PlayerDbModel)

    @Query("SELECT * FROM players WHERE name = :name LIMIT 1")
    suspend fun getPlayerByName(name: String): PlayerDbModel?
}