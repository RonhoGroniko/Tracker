package com.example.tracker.data.mappers

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