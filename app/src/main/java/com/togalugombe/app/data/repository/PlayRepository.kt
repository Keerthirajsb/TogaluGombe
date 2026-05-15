package com.togalugombe.app.data.repository

import com.togalugombe.app.data.local.dao.PlayDao
import com.togalugombe.app.data.local.entity.PlayEntity
import com.togalugombe.app.data.local.entity.SceneEntity
import kotlinx.coroutines.flow.Flow

class PlayRepository(private val playDao: PlayDao) {
    fun getAllPlays(): Flow<List<PlayEntity>> = playDao.getAllPlays()
    suspend fun getPlayById(id: Int): PlayEntity? = playDao.getPlayById(id)
    fun getScenesForPlay(playId: Int): Flow<List<SceneEntity>> = playDao.getScenesForPlay(playId)
    suspend fun getSceneById(id: Int): SceneEntity? = playDao.getSceneById(id)
    suspend fun getPlayCount(): Int = playDao.getPlayCount()
    suspend fun insertPlays(plays: List<PlayEntity>) = playDao.insertPlays(plays)
    suspend fun insertScenes(scenes: List<SceneEntity>) = playDao.insertScenes(scenes)
}
