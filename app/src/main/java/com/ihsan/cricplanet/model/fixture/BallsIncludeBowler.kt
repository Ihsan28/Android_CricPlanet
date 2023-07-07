package com.ihsan.cricplanet.model.fixture

import android.os.Parcelable
import com.ihsan.cricplanet.model.Score
import com.ihsan.cricplanet.model.Team
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BallsIncludeBowler(
    val ball: Double?,
    val batsman: Batsman?,
    val batsman_id: Int?,
    val batsman_one_on_creeze_id: Int?,
    val batsman_two_on_creeze_id: Int?,
    val batsmanout_id: Int?,
    val bowler: Bowler?,
    val bowler_id: Int?,
    val catchstump_id: Int?,
    val fixture_id: Int?,
    val id: Int,
    val resource: String?,
    val runout_by_id: Int?,
    val score: Score?,
    val score_id: Int?,
    val scoreboard: String?,
    val team: Team?,
    val team_id: Int?,
    val updated_at: String?
) : Parcelable {
    constructor() : this(
        null,
        null,
        null,
        null,
        null,
        null,
        null,
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
    )
}