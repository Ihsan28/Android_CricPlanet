package com.ihsan.cricplanet.model.fixture

import android.os.Parcelable
import com.ihsan.cricplanet.model.Position
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Batsman(
    val battingstyle: String?,
    val bowlingstyle: String?,
    val country_id: Int?,
    val dateofbirth: String?,
    val firstname: String?,
    val fullname: String?,
    val gender: String?,
    val id: Int,
    val image_path: String?,
    val lastname: String?,
    val position: Position?,
    val resource: String?,
    val updated_at: String?
):Parcelable
{
    constructor():this(
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
        null
    )
}