package com.ihsan.cricplanet.model.team

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GlobalTeamRankingList(val ranking:List<GlobalTeamRanking>?):Parcelable
{
    constructor():this(null)
}
