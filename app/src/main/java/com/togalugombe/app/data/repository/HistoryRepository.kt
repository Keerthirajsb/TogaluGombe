package com.togalugombe.app.data.repository

import com.togalugombe.app.data.local.dao.HistoryVideoDao
import com.togalugombe.app.data.local.entity.HistoryVideoEntity
import kotlinx.coroutines.flow.Flow

class HistoryRepository(private val historyVideoDao: HistoryVideoDao) {
    fun getAllVideos(): Flow<List<HistoryVideoEntity>> = historyVideoDao.getAllVideos()
    fun getVideosByCategory(category: String): Flow<List<HistoryVideoEntity>> = historyVideoDao.getVideosByCategory(category)
    suspend fun getVideoById(id: Int): HistoryVideoEntity? = historyVideoDao.getVideoById(id)
    fun getAllCategories(): Flow<List<String>> = historyVideoDao.getAllCategories()
    suspend fun insertVideos(videos: List<HistoryVideoEntity>) = historyVideoDao.insertVideos(videos)
}
