package com.togalugombe.app.ui.screens.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.togalugombe.app.data.local.AppDatabase
import com.togalugombe.app.data.local.DatabaseSeeder
import com.togalugombe.app.data.repository.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class HomeUiState(
    val playCount: Int = 0,
    val puppetCount: Int = 0,
    val artistCount: Int = 0,
    val videoCount: Int = 0
)

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)
    private val playRepo = PlayRepository(db.playDao())
    private val puppetRepo = PuppetRepository(db.puppetDao())
    private val artistRepo = ArtistRepository(db.artistDao())
    private val historyRepo = HistoryRepository(db.historyVideoDao())

    private val _isKannada = MutableStateFlow(false)
    val isKannada: StateFlow<Boolean> = _isKannada.asStateFlow()

    val uiState: StateFlow<HomeUiState> = combine(
        playRepo.getAllPlays().map { it.size },
        puppetRepo.getAllPuppets().map { it.size },
        artistRepo.getAllArtists().map { it.size },
        historyRepo.getAllVideos().map { it.size }
    ) { plays, puppets, artists, videos ->
        HomeUiState(plays, puppets, artists, videos)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), HomeUiState())

    init {
        viewModelScope.launch {
            val count = withContext(Dispatchers.IO) { playRepo.getPlayCount() }
            if (count == 0) {
                withContext(Dispatchers.IO) {
                    seedDatabase()
                }
            }
        }
    }

    fun toggleLanguage() {
        _isKannada.value = !_isKannada.value
    }

    private suspend fun seedDatabase() {
        // Run all seeds in a single transaction-like block
        db.runInTransaction {
            launch(Dispatchers.IO) {
                playRepo.insertPlays(DatabaseSeeder.getPlays())
                playRepo.insertScenes(DatabaseSeeder.getScenes())
                puppetRepo.insertPuppets(DatabaseSeeder.getPuppets())
                artistRepo.insertArtists(DatabaseSeeder.getArtists())
                artistRepo.insertWorkshops(DatabaseSeeder.getWorkshops())
                historyRepo.insertVideos(DatabaseSeeder.getHistoryVideos())
            }
        }
    }
}
