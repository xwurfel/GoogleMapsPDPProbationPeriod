package com.mike_ilnytskyi.google_maps_pdp_probation_period.di.poi

import com.mike_ilnytskyi.google_maps_pdp_probation_period.data.poi.dao.PoiDao
import com.mike_ilnytskyi.google_maps_pdp_probation_period.data.poi.repository.PoiRepositoryImpl
import com.mike_ilnytskyi.google_maps_pdp_probation_period.data.poi.source.PoiDataSource
import com.mike_ilnytskyi.google_maps_pdp_probation_period.domain.poi.repository.PoiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object PoiModule {

    @Provides
    fun providePoiDataSource(poiDao: PoiDao): PoiDataSource {
        return PoiDataSource(poiDao)
    }

    @Provides
    fun providePoiRepository(poiDataSource: PoiDataSource): PoiRepository {
        return PoiRepositoryImpl(poiDataSource)
    }
}