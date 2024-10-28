package com.mike_ilnytskyi.google_maps_pdp_probation_period.presentation.poi_details

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mike_ilnytskyi.google_maps_pdp_probation_period.domain.poi.model.PointOfInterest
import com.mike_ilnytskyi.google_maps_pdp_probation_period.domain.poi.repository.PoiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PoiDetailsViewModel @Inject constructor(
    private val poiRepository: PoiRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(PoiDetailsUiState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    fun onIdChanged(id: Long) {
        _uiState.update { it.copy(initialId = id, isLoading = false) }
    }

    fun onTitleChanged(newTitle: String) {
        _uiState.update { it.copy(poi = it.poi?.copy(title = newTitle)) }
    }

    fun onDescriptionChanged(newDescription: String) {
        _uiState.update { it.copy(poi = it.poi?.copy(description = newDescription)) }
    }

    fun onImageSelected(imageUri: Uri) {
        _uiState.update { it.copy(poi = it.poi?.copy(imageUri = imageUri)) }
    }

    fun savePoi() {
        val currentState = _uiState.value
        val poi = currentState.poi
        poi?.let {
            viewModelScope.launch {
                try {
                    poiRepository.savePoi(poi)
                    _uiState.update { it.copy(isUpdated = true) }
                } catch (e: Exception) {
                    ensureActive()
                    _uiState.update { it.copy(errorMessage = e.message) }
                }
            }
        }
    }

    fun deletePoi() {
        viewModelScope.launch {
            try {
                _uiState.value.poi?.let { poi ->
                    poiRepository.deletePoi(poi.id)
                    _uiState.update { it.copy(isDeleted = true) }
                }
            } catch (e: Exception) {
                ensureActive()
                _uiState.update { it.copy(errorMessage = e.message) }
            }
        }
    }

    data class PoiDetailsUiState(
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
        val initialId: Long = 0,
        val poi: PointOfInterest? = null,
        val isUpdated: Boolean = false,
        val isDeleted: Boolean = false,
    )
}