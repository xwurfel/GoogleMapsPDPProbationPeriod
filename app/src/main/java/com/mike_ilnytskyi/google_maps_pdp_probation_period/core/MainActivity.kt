package com.mike_ilnytskyi.google_maps_pdp_probation_period.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.mike_ilnytskyi.google_maps_pdp_probation_period.navigation.RootNavigator
import com.mike_ilnytskyi.google_maps_pdp_probation_period.ui.theme.GoogleMapsPDPProbationPeriodTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            GoogleMapsPDPProbationPeriodTheme {
                RootNavigator()
            }
        }
    }
}

