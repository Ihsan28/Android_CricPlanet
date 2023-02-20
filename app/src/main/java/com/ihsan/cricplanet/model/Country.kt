package com.ihsan.cricplanet.model

data class Country(
    val continent_id: Int?,
    val id: Int,
    val name: String?,
    val resource: String?,
    val updated_at: Any?
)
{
    constructor():this(
        null,
        0,
        null,
        null,
        null,
    )
}