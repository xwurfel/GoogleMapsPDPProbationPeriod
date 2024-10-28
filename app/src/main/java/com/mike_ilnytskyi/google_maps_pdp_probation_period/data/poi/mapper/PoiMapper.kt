package com.mike_ilnytskyi.google_maps_pdp_probation_period.data.poi.mapper

import android.net.Uri
import com.google.android.gms.maps.model.LatLng
import com.mike_ilnytskyi.google_maps_pdp_probation_period.data.poi.entity.PoiEntity
import com.mike_ilnytskyi.google_maps_pdp_probation_period.domain.poi.model.PointOfInterest

fun PoiEntity.toDomain(): PointOfInterest {
    return PointOfInterest(
        id = id,
        title = title,
        description = description,
        imageUri = imageUriString?.let { Uri.parse(it) },
        location = LatLng(latitude, longitude)
    )
}

fun PointOfInterest.toEntity(): PoiEntity {
    return PoiEntity(
        id = id,
        title = title,
        description = description,
        imageUriString = imageUri?.toString(),
        latitude = location.latitude,
        longitude = location.longitude
    )
}