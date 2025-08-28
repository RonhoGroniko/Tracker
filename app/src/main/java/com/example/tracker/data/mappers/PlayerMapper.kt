package com.example.tracker.data.mappers

import com.example.tracker.data.db.models.PlayerDbModel
import com.example.tracker.data.network.models.player_name_response.PlayerAttributesDto
import com.example.tracker.data.network.models.player_name_response.PlayerResponseDto
import com.example.tracker.domain.models.PlayerInfoEntity


fun PlayerResponseDto.toPlayerInfoEntity(): PlayerInfoEntity {
    val id = data.first().id
    val playerInfo: PlayerAttributesDto = data.first().attributes
    return PlayerInfoEntity(
        id = id,
        banType = playerInfo.banType,
        clanId = playerInfo.clanId,
        name = playerInfo.name
    )
}

fun PlayerDbModel.toPlayerInfoEntity() = PlayerInfoEntity(
    id = playerId,
    banType = banType,
    clanId = clanId,
    name = name
)

fun PlayerInfoEntity.toPlayerDbModel() = PlayerDbModel(
    playerId = id,
    banType = banType,
    clanId = clanId,
    name = name
)