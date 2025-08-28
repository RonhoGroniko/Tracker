package com.example.tracker.di

import android.app.Application
import com.example.tracker.ui.GameFragment
import com.example.tracker.ui.GameStatsFragment
import com.example.tracker.ui.TrackerApp
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class,
        WorkerModule::class
    ]
)
interface ApplicationComponent {

    fun inject(application: TrackerApp)

    fun inject(fragment: GameFragment)

    fun inject(fragment: GameStatsFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}