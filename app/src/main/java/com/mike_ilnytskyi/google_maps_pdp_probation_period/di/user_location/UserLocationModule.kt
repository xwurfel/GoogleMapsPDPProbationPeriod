package com.mike_ilnytskyi.google_maps_pdp_probation_period.di.user_location

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.mike_ilnytskyi.google_maps_pdp_probation_period.data.user_location.DefaultUserLocationRepository
import com.mike_ilnytskyi.google_maps_pdp_probation_period.domain.user_location.repository.UserLocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserLocationModule {
    @Provides
    fun provideFusedLocationProviderClient(@ApplicationContext context: Context): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }

    @Singleton
    @Provides
    fun provideUserLocationRepository(
        fusedLocationProviderClient: FusedLocationProviderClient
    ): UserLocationRepository {
        return DefaultUserLocationRepository(fusedLocationProviderClient)
    }
}