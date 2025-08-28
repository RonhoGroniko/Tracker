package com.example.tracker.di

import androidx.lifecycle.ViewModel
import com.example.tracker.ui.GameStatsViewModel
import com.example.tracker.ui.GameViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(GameViewModel::class)
    @Binds
    fun bindGameViewModel(impl: GameViewModel): ViewModel

    @IntoMap
    @ViewModelKey(GameStatsViewModel::class)
    @Binds
    fun bindGameStatsViewModel(impl: GameStatsViewModel): ViewModel
}