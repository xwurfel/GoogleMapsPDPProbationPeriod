package com.mike_ilnytskyi.google_maps_pdp_probation_period.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.mike_ilnytskyi.google_maps_pdp_probation_period.presentation.home.HomeScreenRoute
import com.mike_ilnytskyi.google_maps_pdp_probation_period.presentation.poi_details.PoiDetailsScreenRoute
import com.mike_ilnytskyi.google_maps_pdp_probation_period.presentation.save_poi.PoiSettingsScreenRoute


@Composable
fun RootNavigator() {
    val navController = rememberNavController()
    NavHost(navController = navController, Destinations.Home) {
        composable<Destinations.Home> {
            HomeScreenRoute(
                onNavigate = {
                    navController.navigate(it)
                }
            )
        }

        composable<Destinations.PoiSettings> {
            val latitude = it.toRoute<Destinations.PoiSettings>().latitude
            val longitude = it.toRoute<Destinations.PoiSettings>().longitude
            PoiSettingsScreenRoute(latitude, longitude, onBack = navController::navigateUp)
        }

        composable<Destinations.PoiDetails> {
            val poiId = it.toRoute<Destinations.PoiDetails>().poiId
            PoiDetailsScreenRoute(poiId, onBack = navController::navigateUp)
        }
    }

}