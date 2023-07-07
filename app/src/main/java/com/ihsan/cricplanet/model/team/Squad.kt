package com.ihsan.cricplanet.model.team

import android.os.Parcelable
import com.ihsan.cricplanet.model.Position
import kotlinx.parcelize.Parcelize

@Parcelize
data class Squad(
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
    val squad: SquadX?,
    val updated_at: String?
) : Parcelable