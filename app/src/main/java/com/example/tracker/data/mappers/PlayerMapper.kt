package com.example.tracker.data.mappers

import com.example.tracker.data.network.models.PlayerAttributesDto
import com.example.tracker.data.network.models.PlayerResponseDto
import com.example.tracker.domain.models.PlayerInfoEntity

class PlayerMapper {

    fun mapDtoToEntity(dto: PlayerResponseDto): PlayerInfoEntity {
        val playerInfo: PlayerAttributesDto = dto.data.first().attributes
        return PlayerInfoEntity(
            banType = playerInfo.banType,
            clanId = playerInfo.clanId,
            name = playerInfo.name
        )
    }
}