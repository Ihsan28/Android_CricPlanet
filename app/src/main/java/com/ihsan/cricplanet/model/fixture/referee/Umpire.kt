package com.ihsan.cricplanet.model.fixture.referee

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Umpire(
    val country_id: Int?,
    val dateofbirth: String?,
    val firstname: String?,
    val fullname: String?,
    val gender: String?,
    val id: Int,
    val lastname: String?,
    val resource: String?,
    val updated_at: String?
) : Parcelable {
    constructor() : this(
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