package com.mike_ilnytskyi.google_maps_pdp_probation_period.di.database

import android.content.Context
import androidx.room.Room
import com.mike_ilnytskyi.google_maps_pdp_probation_period.core.AppDatabase
import com.mike_ilnytskyi.google_maps_pdp_probation_period.data.poi.dao.PoiDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun providePoiDao(database: AppDatabase): PoiDao {
        return database.poiDao()
    }
}