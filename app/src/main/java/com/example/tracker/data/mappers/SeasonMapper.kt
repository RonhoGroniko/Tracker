package com.example.tracker.data.mappers

import com.example.tracker.data.db.models.SeasonDbModel
import com.example.tracker.data.network.models.season.SeasonDataDto
import com.example.tracker.data.network.models.season.SeasonResponseDto
import com.example.tracker.domain.models.SeasonInfoEntity


fun SeasonInfoEntity.toSeasonDbModel() = SeasonDbModel(
    id = id,
    isCurrentSeason = isCurrentSeason
)

fun SeasonDbModel.toSeasonInfoEntity() = SeasonInfoEntity(
    id = id,
    isCurrentSeason = isCurrentSeason
)

fun SeasonDataDto.toSeasonInfoEntity(): SeasonInfoEntity {
    return SeasonInfoEntity(
        id = id,
        isCurrentSeason = attributes.isCurrentSeason
    )
}

fun SeasonResponseDto.toSeasonInfoEntityList(): List<SeasonInfoEntity> {
    return data.map { it.toSeasonInfoEntity() }
}
