package com.mike_ilnytskyi.google_maps_pdp_probation_period.domain.user_location.repository

import android.location.Location

interface UserLocationRepository {
    suspend fun getCurrentLocation(): Location?
}