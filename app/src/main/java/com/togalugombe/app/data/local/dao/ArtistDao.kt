package com.togalugombe.app.data.local.dao

import androidx.room.*
import com.togalugombe.app.data.local.entity.ArtistEntity
import com.togalugombe.app.data.local.entity.WorkshopEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtistDao {
    @Query("SELECT * FROM artists ORDER BY name")
    fun getAllArtists(): Flow<List<ArtistEntity>>

    @Query("SELECT * FROM artists WHERE id = :artistId")
    suspend fun getArtistById(artistId: Int): ArtistEntity?

    @Query("SELECT * FROM workshops ORDER BY date")
    fun getAllWorkshops(): Flow<List<WorkshopEntity>>

    @Query("SELECT * FROM workshops WHERE artistId = :artistId")
    fun getWorkshopsByArtist(artistId: Int): Flow<List<WorkshopEntity>>

    @Query("SELECT * FROM workshops WHERE id = :workshopId")
    suspend fun getWorkshopById(workshopId: Int): WorkshopEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtists(artists: List<ArtistEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkshops(workshops: List<WorkshopEntity>)

    @Query("UPDATE workshops SET seatsAvailable = seatsAvailable - 1 WHERE id = :workshopId AND seatsAvailable > 0")
    suspend fun bookWorkshopSeat(workshopId: Int)
}
