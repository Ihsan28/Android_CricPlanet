package com.ihsan.cricplanet.model.responseapi

import com.ihsan.cricplanet.model.player.PlayerCard

data class ResponsePlayerCard(
    val `data`: List<PlayerCard>
)