package com.ihsan.cricplanet.model.team

data class Ranking(
    val matches: Int?,
    val points: Int?,
    val position: Int?,
    val rating: Int?
)
{
    constructor():this(
        null,
        null,
        null,
        null,
    )
}