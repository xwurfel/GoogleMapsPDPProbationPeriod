package com.mike_ilnytskyi.google_maps_pdp_probation_period.data.poi.source

import com.mike_ilnytskyi.google_maps_pdp_probation_period.data.poi.dao.PoiDao
import com.mike_ilnytskyi.google_maps_pdp_probation_period.data.poi.entity.PoiEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PoiDataSource @Inject constructor(
    private val poiDao: PoiDao
) {
    suspend fun savePoi(poiEntity: PoiEntity) {
        poiDao.insertPoi(poiEntity)
    }

    fun getPois(): Flow<List<PoiEntity>> {
        return poiDao.getPois()
    }

    suspend fun deletePoi(id: Long) {
        poiDao.deletePoi(id)
    }

    fun getPoiById(id: Long): Flow<PoiEntity?> {
        return poiDao.getPoiById(id)
    }

    suspend fun isPoiSaved(id: Long): Boolean {
        return poiDao.isPoiSaved(id)
    }

    fun getPoiByCoordinates(latitude: Double, longitude: Double): Flow<PoiEntity?> {
        return poiDao.getPoiByCoordinates(latitude, longitude)
    }
}