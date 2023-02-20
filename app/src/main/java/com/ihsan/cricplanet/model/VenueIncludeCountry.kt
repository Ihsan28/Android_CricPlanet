package com.ihsan.cricplanet.model

data class VenueIncludeCountry(
    val capacity: Int?,
    val city: String?,
    val country_id: Int?,
    val floodlight: Boolean?,
    val id: Int,
    val image_path: String?,
    val name: String?,
    val resource: String?,
    val country: Country?,
    val updated_at: String?
) {
    constructor() : this(
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