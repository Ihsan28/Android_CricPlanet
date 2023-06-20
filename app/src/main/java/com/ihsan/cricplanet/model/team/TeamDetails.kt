package com.ihsan.cricplanet.model.team

import android.os.Parcelable
import com.ihsan.cricplanet.model.Country
import com.ihsan.cricplanet.model.fixture.Fixture
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeamDetails(
    val code: String?,
    val country: Country?,
    val country_id: Int?,
    val fixtures: List<Fixture>?,
    val id: Int,
    val image_path: String?,
    val name: String?,
    val national_team: Boolean?,
    val resource: String?,
    val results: List<Fixture>?,
    val squad: List<Squad>?,
    val updated_at: String?
) : Parcelable