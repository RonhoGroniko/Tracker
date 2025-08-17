package com.example.tracker.data.mappers

import com.example.tracker.data.db.models.SeasonDbModel
import com.example.tracker.data.network.models.season.SeasonDataDto
import com.example.tracker.data.network.models.season.SeasonResponseDto
import com.example.tracker.domain.models.SeasonInfoEntity
import com.example.tracker.ui.models.SeasonInfoUiModel


fun SeasonInfoEntity.toSeasonDbModel() = SeasonDbModel(
    id = id,
    name = id.mapIdToName(),
    isCurrentSeason = isCurrentSeason
)

fun SeasonDbModel.toSeasonInfoEntity() = SeasonInfoEntity(
    id = id,
    name = name,
    isCurrentSeason = isCurrentSeason
)

fun SeasonDataDto.toSeasonInfoEntity(): SeasonInfoEntity {
    return SeasonInfoEntity(
        id = id,
        name = id.mapIdToName(),
        isCurrentSeason = attributes.isCurrentSeason
    )
}

fun SeasonResponseDto.toSeasonInfoEntityList(): List<SeasonInfoEntity> {
    return data.map { it.toSeasonInfoEntity() }
}

fun SeasonInfoEntity.toSeasonInfoUiModel() = SeasonInfoUiModel(
    id = id,
    name = name
)

private fun String.mapIdToName(): String {
    return substringAfter("official.")
}