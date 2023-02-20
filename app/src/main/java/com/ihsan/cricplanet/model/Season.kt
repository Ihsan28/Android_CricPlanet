package com.ihsan.cricplanet.model

data class Season(
    val code: String?,
    val id: Int,
    val league_id: Int?,
    val name: String?,
    val resource: String?,
    val updated_at: String?
)
{
    constructor():this(
        null,
        0,
        null,
        null,
        null,
        null
    )
}