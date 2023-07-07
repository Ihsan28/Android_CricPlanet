package com.ihsan.cricplanet.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Score(
    val ball: Boolean?,
    val bye: Int?,
    val four: Boolean?,
    val id: Int,
    val is_wicket: Boolean?,
    val leg_bye: Int?,
    val name: String?,
    val noball: Int?,
    val noball_runs: Int?,
    val `out`: Boolean?,
    val resource: String?,
    val runs: Int?,
    val six: Boolean?
) : Parcelable {
    constructor() : this(
        null,
        null,
        null,
        0,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
    )
}