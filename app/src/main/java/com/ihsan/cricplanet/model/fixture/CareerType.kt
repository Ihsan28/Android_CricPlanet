package com.ihsan.cricplanet.model.fixture

import com.ihsan.cricplanet.model.player.careerstats.Batting

data class CareerType(
    var testMatches: MutableList<Batting>?,
    var odiMatches: MutableList<Batting>?,
    var t20Matches: MutableList<Batting>?,
    var leagueMatches: MutableList<Batting>?,
    var t20IMatches: MutableList<Batting>?
)