package com.ihsan.cricplanet.model.fixture

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamDlData(
    val overs: Float?,
    val score: Int?,
    val wickets_out: Int?
):Parcelable
{
    constructor():this(
        null,
        null,
        null
    )
}