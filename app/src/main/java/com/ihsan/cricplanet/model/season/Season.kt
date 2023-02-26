package com.ihsan.cricplanet.model.season

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Season(
    val code: String?,
    val id: Int,
    val league_id: Int?,
    val name: String?,
    val resource: String?,
    val updated_at: String?
): Parcelable
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