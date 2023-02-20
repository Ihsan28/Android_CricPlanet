package com.ihsan.cricplanet.model.fixture.result.teamwontoss

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TossWon(
    val code: String?,
    val country_id: Int?,
    val id: Int,
    val image_path: String?,
    val name: String?,
    val national_team: Boolean?,
    val resource: String?,
    val updated_at: String?
):Parcelable
{
    constructor():this(
        null,
        null,
        0,
        null,
        null,
        null,
        null,
        null
    )
}