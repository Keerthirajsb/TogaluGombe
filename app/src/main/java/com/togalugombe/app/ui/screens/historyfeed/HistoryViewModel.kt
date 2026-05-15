package com.togalugombe.app.ui.screens.historyfeed

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.togalugombe.app.data.local.AppDatabase
import com.togalugombe.app.data.repository.HistoryRepository
import kotlinx.coroutines.flow.*

class HistoryViewModel(application: Application) : AndroidViewModel(application) {
    private val historyRepo = HistoryRepository(AppDatabase.getDatabase(application).historyVideoDao())

    val allVideos = historyRepo.getAllVideos().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val categories = historyRepo.getAllCategories().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory: StateFlow<String?> = _selectedCategory.asStateFlow()

    private val _isKannada = MutableStateFlow(false)
    val isKannada: StateFlow<Boolean> = _isKannada.asStateFlow()

    val filteredVideos = combine(allVideos, _selectedCategory) { videos, category ->
        if (category == null) videos else videos.filter { it.category == category }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun setCategory(category: String?) { _selectedCategory.value = category }
    fun toggleLanguage() { _isKannada.value = !_isKannada.value }
}
