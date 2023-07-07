package com.ihsan.cricplanet.model.fixture

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamDlData(
    val overs: Double?,
    val score: Int?,
    val wickets_out: Double?
) : Parcelable {
    constructor() : this(
        null,
        null,
        null
    )
}