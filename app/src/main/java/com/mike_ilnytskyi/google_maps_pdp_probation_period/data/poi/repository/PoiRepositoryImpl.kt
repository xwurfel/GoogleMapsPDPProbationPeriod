package com.mike_ilnytskyi.google_maps_pdp_probation_period.data.poi.repository

import com.mike_ilnytskyi.google_maps_pdp_probation_period.data.poi.mapper.toDomain
import com.mike_ilnytskyi.google_maps_pdp_probation_period.data.poi.mapper.toEntity
import com.mike_ilnytskyi.google_maps_pdp_probation_period.data.poi.source.PoiDataSource
import com.mike_ilnytskyi.google_maps_pdp_probation_period.domain.poi.model.PointOfInterest
import com.mike_ilnytskyi.google_maps_pdp_probation_period.domain.poi.repository.PoiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PoiRepositoryImpl @Inject constructor(
    private val poiDataSource: PoiDataSource
) : PoiRepository {
    override suspend fun savePoi(poi: PointOfInterest) {
        poiDataSource.savePoi(poi.toEntity())
    }

    override fun getPois(): Flow<List<PointOfInterest>> {
        return poiDataSource.getPois().map { pois ->
            pois.map { it.toDomain() }
        }
    }

    override suspend fun deletePoi(id: Long) {
        poiDataSource.deletePoi(id)
    }

    override fun getPoiById(id: Long): Flow<PointOfInterest?> {
        return poiDataSource.getPoiById(id).map { it?.toDomain() }
    }

    override suspend fun isPoiSaved(id: Long): Boolean {
        return poiDataSource.isPoiSaved(id)
    }

    override fun getPoiByCoordinates(latitude: Double, longitude: Double): Flow<PointOfInterest?> {
        return poiDataSource.getPoiByCoordinates(latitude, longitude).map { it?.toDomain() }
    }
}