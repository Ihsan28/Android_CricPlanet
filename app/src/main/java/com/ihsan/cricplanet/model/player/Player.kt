package com.ihsan.cricplanet.model.player

import android.os.Parcelable
import com.ihsan.cricplanet.model.Position
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    val battingstyle: String?,
    val bowlingstyle: String?,
    val country_id: Int,
    val dateofbirth: String,
    val firstname: String,
    val fullname: String,
    val gender: String,
    val id: Int,
    val image_path: String,
    val lastname: String,
    val position: Position,
    val resource: String,
    val updated_at: String
) : Parcelable {
    constructor() : this(
        "N/A",
        "N/A",
        0,
        "N/A",
        "N/A",
        "N/A",
        "N/A",
        0,
        "N/A",
        "N/A",
        Position(0, "N/A", "N/A"),
        "N/A",
        "N/A",
    )
}