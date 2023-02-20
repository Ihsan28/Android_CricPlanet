package com.ihsan.cricplanet.model.slider

import com.google.gson.annotations.SerializedName
import com.ihsan.cricplanet.model.fixture.FixtureIncludeForLiveCard
import java.io.Serializable

data class FixtureIncludeForCardSlider(
    @SerializedName("liveFixtures")
    val liveFixtures: List<FixtureIncludeForLiveCard>?= listOf(),
) : Serializable
