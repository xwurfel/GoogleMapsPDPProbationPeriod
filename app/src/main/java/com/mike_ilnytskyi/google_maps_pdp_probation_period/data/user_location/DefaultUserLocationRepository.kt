package com.mike_ilnytskyi.google_maps_pdp_probation_period.data.user_location

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.mike_ilnytskyi.google_maps_pdp_probation_period.domain.user_location.repository.UserLocationRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class DefaultUserLocationRepository @Inject constructor(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
) : UserLocationRepository {
    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): Location? {
        return suspendCancellableCoroutine { continuation ->
            fusedLocationProviderClient.lastLocation
                .addOnSuccessListener { location ->
                    continuation.resume(location)
                }
                .addOnFailureListener { exception ->
                    continuation.resumeWithException(exception)
                }
        }
    }
}