package com.togalugombe.app.data.local.dao

import androidx.room.*
import com.togalugombe.app.data.local.entity.PlayEntity
import com.togalugombe.app.data.local.entity.SceneEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayDao {
    @Query("SELECT * FROM plays ORDER BY id")
    fun getAllPlays(): Flow<List<PlayEntity>>

    @Query("SELECT * FROM plays WHERE id = :playId")
    suspend fun getPlayById(playId: Int): PlayEntity?

    @Query("SELECT * FROM scenes WHERE playId = :playId ORDER BY sceneNumber")
    fun getScenesForPlay(playId: Int): Flow<List<SceneEntity>>

    @Query("SELECT * FROM scenes WHERE id = :sceneId")
    suspend fun getSceneById(sceneId: Int): SceneEntity?

    @Query("SELECT COUNT(*) FROM plays")
    suspend fun getPlayCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlays(plays: List<PlayEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScenes(scenes: List<SceneEntity>)
}
