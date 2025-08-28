package com.example.tracker.di

import com.example.tracker.data.workers.ChildWorkerFactory
import com.example.tracker.data.workers.RefreshSeasonsWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

    @IntoMap
    @WorkerKey(RefreshSeasonsWorker::class)
    @Binds
    fun bindRefreshSeasonsWorkerFactory(impl: RefreshSeasonsWorker.Factory): ChildWorkerFactory
}