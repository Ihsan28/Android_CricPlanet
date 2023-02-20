package com.ihsan.cricplanet.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "team")
data class Team(
    val code: String?,
    val country_id: Int?,
    @PrimaryKey
    val id: Int,
    val image_path: String?,
    val name: String?,
    val national_team: Boolean?,
    val resource: String?,
    val updated_at: String?
):Parcelable
{
    constructor() : this(
        null,
        null,
        0,
        null,
        null,
        null,
        null,
        null,
    )
}