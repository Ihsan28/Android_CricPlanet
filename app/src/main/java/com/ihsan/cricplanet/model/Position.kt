package com.ihsan.cricplanet.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Position(
    val id: Int,
    val name: String?,
    val resource: String?
) : Parcelable {
    constructor() : this(
        0,
        null,
        null,
    )
}