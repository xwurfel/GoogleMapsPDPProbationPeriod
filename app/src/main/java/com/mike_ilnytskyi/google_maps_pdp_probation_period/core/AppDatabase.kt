package com.mike_ilnytskyi.google_maps_pdp_probation_period.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mike_ilnytskyi.google_maps_pdp_probation_period.data.poi.dao.PoiDao
import com.mike_ilnytskyi.google_maps_pdp_probation_period.data.poi.entity.PoiEntity

@Database(entities = [PoiEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun poiDao(): PoiDao
}