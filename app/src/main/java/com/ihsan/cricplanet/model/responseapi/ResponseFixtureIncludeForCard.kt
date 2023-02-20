package com.ihsan.cricplanet.model.responseapi

import com.ihsan.cricplanet.model.fixture.FixtureIncludeForCard

data class ResponseFixtureIncludeForCard(
    val `data`: List<FixtureIncludeForCard>,
    val links: Links,
    val meta: Meta
)