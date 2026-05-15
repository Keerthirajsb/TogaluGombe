package com.togalugombe.app.ui.screens.liveassist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.togalugombe.app.data.local.AppDatabase
import com.togalugombe.app.data.local.entity.PlayEntity
import com.togalugombe.app.data.local.entity.SceneEntity
import com.togalugombe.app.data.repository.PlayRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LiveAssistViewModel(application: Application) : AndroidViewModel(application) {
    private val playRepo = PlayRepository(AppDatabase.getDatabase(application).playDao())

    val plays = playRepo.getAllPlays().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _selectedPlay = MutableStateFlow<PlayEntity?>(null)
    val selectedPlay: StateFlow<PlayEntity?> = _selectedPlay.asStateFlow()

    private val _scenes = MutableStateFlow<List<SceneEntity>>(emptyList())
    val scenes: StateFlow<List<SceneEntity>> = _scenes.asStateFlow()

    private val _isKannada = MutableStateFlow(false)
    val isKannada: StateFlow<Boolean> = _isKannada.asStateFlow()

    fun loadScenesForPlay(playId: Int) {
        viewModelScope.launch {
            _selectedPlay.value = playRepo.getPlayById(playId)
            playRepo.getScenesForPlay(playId).collect { _scenes.value = it }
        }
    }

    fun toggleLanguage() { _isKannada.value = !_isKannada.value }
}
