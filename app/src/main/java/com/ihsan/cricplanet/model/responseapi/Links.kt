package com.ihsan.cricplanet.model.responseapi

data class Links(
    val first: String?,
    val last: String?,
    val next: String?,
    val prev: Any?
) {
    constructor() : this(
        null,
        null,
        null,
        null,
    )
}