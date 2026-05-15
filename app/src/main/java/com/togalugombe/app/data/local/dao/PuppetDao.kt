package com.togalugombe.app.data.local.dao

import androidx.room.*
import com.togalugombe.app.data.local.entity.PuppetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PuppetDao {
    @Query("SELECT * FROM puppets ORDER BY nameEn")
    fun getAllPuppets(): Flow<List<PuppetEntity>>

    @Query("SELECT * FROM puppets WHERE id = :puppetId")
    suspend fun getPuppetById(puppetId: Int): PuppetEntity?

    @Query("SELECT * FROM puppets WHERE characterType = :type")
    fun getPuppetsByType(type: String): Flow<List<PuppetEntity>>

    @Query("SELECT * FROM puppets WHERE playName = :playName")
    fun getPuppetsByPlay(playName: String): Flow<List<PuppetEntity>>

    @Query("SELECT * FROM puppets WHERE nameEn LIKE '%' || :query || '%' OR nameKn LIKE '%' || :query || '%'")
    fun searchPuppets(query: String): Flow<List<PuppetEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPuppets(puppets: List<PuppetEntity>)
}
