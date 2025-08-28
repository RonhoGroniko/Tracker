package com.example.tracker.data.workers

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkerParameters
import com.example.tracker.domain.usecases.AddSeasonUseCase
import com.example.tracker.domain.usecases.GetSeasonListUseCase
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RefreshSeasonsWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val getSeasonListUseCase: GetSeasonListUseCase,
    private val addSeasonUseCase: AddSeasonUseCase
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        return try {
            getSeasonListUseCase().forEach {
                addSeasonUseCase(it)
            }
            Result.success()
        } catch (e: Exception) {
            Log.d(TAG, e.message.toString())
            Result.retry()
        }
    }

    class Factory @Inject constructor(
        private val getSeasonListUseCase: GetSeasonListUseCase,
        private val addSeasonUseCase: AddSeasonUseCase
    ): ChildWorkerFactory {

        override fun create(
            context: Context,
            workerParameters: WorkerParameters
        ): ListenableWorker = RefreshSeasonsWorker(
            context = context,
            workerParameters = workerParameters,
            getSeasonListUseCase = getSeasonListUseCase,
            addSeasonUseCase = addSeasonUseCase
        )
    }

    companion object {

        const val NAME = "RefreshSeasonsWorker"
        private const val TAG = "RefreshSeasonsWorker"

        fun makeRequest(): PeriodicWorkRequest {
            return PeriodicWorkRequestBuilder<RefreshSeasonsWorker>(
                30,
                TimeUnit.DAYS
            ).apply {
                setConstraints(makeConstraints())
            }.build()
        }

        private fun makeConstraints(): Constraints {
            return Constraints.Builder().apply {
                setRequiredNetworkType(NetworkType.UNMETERED)
            }.build()
        }
    }
}