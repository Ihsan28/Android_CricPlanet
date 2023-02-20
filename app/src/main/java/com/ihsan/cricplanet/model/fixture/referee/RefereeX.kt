package com.ihsan.cricplanet.model.fixture.referee

data class RefereeX(
    val country_id: Int?,
    val dateofbirth: String?,
    val firstname: String?,
    val fullname: String?,
    val gender: String?,
    val id: Int,
    val lastname: String?,
    val resource: String?,
    val updated_at: String?
)
{
    constructor():this(
        null,
        null,
        null,
        null,
        null,
        0,
        null,
        null,
        null,
    )
}