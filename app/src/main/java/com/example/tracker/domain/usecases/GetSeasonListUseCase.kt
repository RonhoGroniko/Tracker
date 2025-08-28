package com.example.tracker.domain.usecases

import com.example.tracker.domain.models.SeasonInfoEntity
import com.example.tracker.domain.repository.TrackerRepository
import javax.inject.Inject

class GetSeasonListUseCase @Inject constructor (
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(): List<SeasonInfoEntity> {
        return repository.getSeasons()
    }
}