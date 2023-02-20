package com.ihsan.cricplanet.model.fixture.scoreboard.lineup

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LineupX(
    val captain: Boolean?,
    val substitution: Boolean?,
    val team_id: Int?,
    val wicketkeeper: Boolean?
):Parcelable
{
    constructor():this(
        null,
        null,
        null,
        null,
    )
}