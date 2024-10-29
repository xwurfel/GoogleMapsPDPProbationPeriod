package com.mike_ilnytskyi.google_maps_pdp_probation_period.presentation.home.model

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import com.mike_ilnytskyi.google_maps_pdp_probation_period.domain.poi.model.PointOfInterest

class PoiClusterItem(
    val poi: PointOfInterest
) : ClusterItem {
    override fun getPosition(): LatLng = poi.location
    override fun getTitle(): String = poi.title
    override fun getSnippet(): String = poi.description
    override fun getZIndex(): Float? = null
}