package com.example.tracker.ui

import android.app.Application
import androidx.work.Configuration
import com.example.tracker.data.workers.WorkerFactory
import com.example.tracker.di.DaggerApplicationComponent
import javax.inject.Inject

class TrackerApp: Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: WorkerFactory

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }
}