package com.togalugombe.app.data.local.dao

import androidx.room.*
import com.togalugombe.app.data.local.entity.HistoryVideoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryVideoDao {
    @Query("SELECT * FROM history_videos ORDER BY id")
    fun getAllVideos(): Flow<List<HistoryVideoEntity>>

    @Query("SELECT * FROM history_videos WHERE category = :category")
    fun getVideosByCategory(category: String): Flow<List<HistoryVideoEntity>>

    @Query("SELECT * FROM history_videos WHERE id = :videoId")
    suspend fun getVideoById(videoId: Int): HistoryVideoEntity?

    @Query("SELECT DISTINCT category FROM history_videos")
    fun getAllCategories(): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideos(videos: List<HistoryVideoEntity>)
}
