package com.example.tracker.di

import android.app.Application
import com.example.tracker.data.db.AppDatabase
import com.example.tracker.data.db.PlayerDao
import com.example.tracker.data.db.PlayerStatsDao
import com.example.tracker.data.db.SeasonDao
import com.example.tracker.data.network.ApiFactory
import com.example.tracker.data.network.ApiService
import com.example.tracker.data.repository.TrackerRepositoryImpl
import com.example.tracker.domain.repository.TrackerRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindRepository(impl: TrackerRepositoryImpl): TrackerRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }

        @ApplicationScope
        @Provides
        fun provideSeasonDao(application: Application): SeasonDao {
            return AppDatabase.getInstance(application).seasonDao()
        }

        @ApplicationScope
        @Provides
        fun providePlayerDao(application: Application): PlayerDao {
            return AppDatabase.getInstance(application).playerDao()
        }

        @ApplicationScope
        @Provides
        fun providePlayerStatsDao(application: Application): PlayerStatsDao {
            return AppDatabase.getInstance(application).playerStatsDao()
        }
    }
}