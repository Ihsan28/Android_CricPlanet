package com.ihsan.cricplanet.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    val continent_id: Int?,
    val id: Int,
    val image_path: String?,
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