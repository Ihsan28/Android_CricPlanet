package com.ihsan.cricplanet.model

data class Position(
    val id: Int,
    val name: String?,
    val resource: String?
)
{
    constructor():this(
        0,
        null,
        null,
    )
}