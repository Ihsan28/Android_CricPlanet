package com.ihsan.cricplanet.model.player

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InSquad(
    val league_id: Int?,
    val season_id: Int?
) : Parcelable {
    constructor() : this(
        null,
        null,
    )
}