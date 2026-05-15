package com.togalugombe.app.ui.screens.artistconnect

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.togalugombe.app.data.local.AppDatabase
import com.togalugombe.app.data.local.entity.WorkshopEntity
import com.togalugombe.app.data.repository.ArtistRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ArtistViewModel(application: Application) : AndroidViewModel(application) {
    private val artistRepo = ArtistRepository(AppDatabase.getDatabase(application).artistDao())

    val artists = artistRepo.getAllArtists().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val workshops = artistRepo.getAllWorkshops().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _selectedWorkshop = MutableStateFlow<WorkshopEntity?>(null)
    val selectedWorkshop: StateFlow<WorkshopEntity?> = _selectedWorkshop.asStateFlow()

    private val _bookingSuccess = MutableStateFlow(false)
    val bookingSuccess: StateFlow<Boolean> = _bookingSuccess.asStateFlow()

    fun loadWorkshop(workshopId: Int) {
        viewModelScope.launch {
            _selectedWorkshop.value = artistRepo.getWorkshopById(workshopId)
        }
    }

    fun bookWorkshop(workshopId: Int) {
        viewModelScope.launch {
            artistRepo.bookWorkshopSeat(workshopId)
            _bookingSuccess.value = true
            _selectedWorkshop.value = artistRepo.getWorkshopById(workshopId)
        }
    }

    fun resetBookingStatus() { _bookingSuccess.value = false }
}
