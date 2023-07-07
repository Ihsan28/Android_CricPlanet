package com.ihsan.cricplanet.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Venue(
    val capacity: Int?,
    val city: String?,
    val country_id: Int?,
    val floodlight: Boolean?,
    val id: Int,
    val image_path: String?,
    val name: String?,
    val resource: String?,
    val updated_at: String?
) : Parcelable {
    constructor() : this(
        null,
        null,
        null,
        null,
        0,
        null,
        null,
        null,
        null
    )
}