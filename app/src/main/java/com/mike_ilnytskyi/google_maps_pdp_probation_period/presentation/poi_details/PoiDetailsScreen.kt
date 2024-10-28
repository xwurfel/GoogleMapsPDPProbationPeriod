package com.mike_ilnytskyi.google_maps_pdp_probation_period.presentation.poi_details

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.mike_ilnytskyi.google_maps_pdp_probation_period.presentation.common.ErrorScreenContent
import com.mike_ilnytskyi.google_maps_pdp_probation_period.presentation.common.LoadingScreenContent
import com.mike_ilnytskyi.google_maps_pdp_probation_period.presentation.save_poi.ImagePicker

@Composable
fun PoiDetailsScreenRoute(poiId: Long, onBack: () -> Unit) {
    val viewModel: PoiDetailsViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when {
        uiState.isLoading -> {
            LoadingScreenContent(Modifier.fillMaxSize())
        }

        uiState.errorMessage != null -> {
            ErrorScreenContent(uiState.errorMessage ?: "", Modifier.fillMaxSize())
        }

        else -> {
            PoiDetailsScreen(
                uiState,
                onTitleChanged = viewModel::onTitleChanged,
                onDescriptionChanged = viewModel::onDescriptionChanged,
                onImageSelected = viewModel::onImageSelected,
                onSaveClicked = viewModel::savePoi,
                onDeleteClicked = viewModel::deletePoi
            )
        }
    }

    LaunchedEffect(uiState.isUpdated, uiState.isDeleted) {
        if (uiState.isUpdated || uiState.isDeleted) {
            onBack()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.onIdChanged(poiId)
    }
}

@Composable
fun PoiDetailsScreen(
    uiState: PoiDetailsViewModel.PoiDetailsUiState,
    onTitleChanged: (String) -> Unit = {},
    onDescriptionChanged: (String) -> Unit = {},
    onImageSelected: (Uri) -> Unit = {},
    onSaveClicked: () -> Unit = {},
    onDeleteClicked: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(16.dp)
    ) {
        TextField(
            value = uiState.poi?.title ?: "",
            onValueChange = onTitleChanged,
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = uiState.poi?.description ?: "",
            onValueChange = onDescriptionChanged,
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        ImagePicker(onImageSelected = onImageSelected)

        Spacer(modifier = Modifier.height(8.dp))

        uiState.poi?.imageUri?.let { uri ->
            Image(
                painter = rememberAsyncImagePainter(uri),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = onSaveClicked,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }

        uiState.errorMessage?.let { errorMessage ->
            Spacer(modifier = Modifier.height(8.dp))
            Text(errorMessage, color = MaterialTheme.colorScheme.error)
        }
    }
}

@Preview
@Composable
private fun PreviewPoiDetailsScreen() {
    PoiDetailsScreen(PoiDetailsViewModel.PoiDetailsUiState())
}