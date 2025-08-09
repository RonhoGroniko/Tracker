package com.example.tracker.data.network

import com.example.tracker.data.network.models.PlayerResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/players")
    suspend fun getPlayerByName(
        @Query(QUERY_FILTER_PLAYER_NAMES) playerName: String
    ) : PlayerResponseDto


    companion object {

        private const val QUERY_FILTER_PLAYER_NAMES = "filter[playerNames]"
    }
}