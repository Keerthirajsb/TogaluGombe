package com.togalugombe.app.ui.screens.gallery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.togalugombe.app.data.local.AppDatabase
import com.togalugombe.app.data.local.entity.PuppetEntity
import com.togalugombe.app.data.repository.PuppetRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class GalleryViewModel(application: Application) : AndroidViewModel(application) {
    private val puppetRepo = PuppetRepository(AppDatabase.getDatabase(application).puppetDao())

    val allPuppets = puppetRepo.getAllPuppets().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _selectedFilter = MutableStateFlow("all")
    val selectedFilter: StateFlow<String> = _selectedFilter.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedPuppet = MutableStateFlow<PuppetEntity?>(null)
    val selectedPuppet: StateFlow<PuppetEntity?> = _selectedPuppet.asStateFlow()

    private val _isKannada = MutableStateFlow(false)
    val isKannada: StateFlow<Boolean> = _isKannada.asStateFlow()

    val filteredPuppets = combine(allPuppets, _selectedFilter, _searchQuery) { puppets, filter, query ->
        puppets.filter { puppet ->
            val matchesFilter = filter == "all" || puppet.characterType == filter
            val matchesSearch = query.isBlank() || puppet.nameEn.contains(query, true) || puppet.nameKn.contains(query, true)
            matchesFilter && matchesSearch
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun setFilter(filter: String) { _selectedFilter.value = filter }
    fun setSearchQuery(query: String) { _searchQuery.value = query }
    fun toggleLanguage() { _isKannada.value = !_isKannada.value }

    fun loadPuppet(puppetId: Int) {
        viewModelScope.launch {
            _selectedPuppet.value = puppetRepo.getPuppetById(puppetId)
        }
    }
}
