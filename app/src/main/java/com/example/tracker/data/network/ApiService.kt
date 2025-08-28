package com.example.tracker.data.network

import com.example.tracker.data.network.models.player_name_response.PlayerResponseDto
import com.example.tracker.data.network.models.season.SeasonResponseDto
import com.example.tracker.data.network.models.player_season_response.PLayerSeasonResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("players")
    suspend fun getPlayerByName(
        @Query(QUERY_FILTER_PLAYER_NAMES) playerName: String
    ) : PlayerResponseDto

    @GET("seasons")
    suspend fun getSeasons() : SeasonResponseDto

    @GET("players/{playerID}/seasons/{seasonID}")
    suspend fun getPlayerSeasonInfo(
        @Path(PATH_PLAYER_ID) playerID: String,
        @Path(PATH_SEASON_ID) seasonID: String
    ) : PLayerSeasonResponseDto


    companion object {

        private const val PATH_PLAYER_ID = "playerID"
        private const val PATH_SEASON_ID = "seasonID"

        private const val QUERY_FILTER_PLAYER_NAMES = "filter[playerNames]"
    }
}