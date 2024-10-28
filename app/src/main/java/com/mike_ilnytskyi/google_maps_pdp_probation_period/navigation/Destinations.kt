package com.mike_ilnytskyi.google_maps_pdp_probation_period.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Destinations {
    @Serializable
    data object Home : Destinations
    @Serializable
    data class PoiSettings(val latitude: Double, val longitude: Double) : Destinations
    @Serializable
    data class PoiDetails(val poiId: Long) : Destinations
}