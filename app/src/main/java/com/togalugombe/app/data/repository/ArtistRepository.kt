package com.togalugombe.app.data.repository

import com.togalugombe.app.data.local.dao.ArtistDao
import com.togalugombe.app.data.local.entity.ArtistEntity
import com.togalugombe.app.data.local.entity.WorkshopEntity
import kotlinx.coroutines.flow.Flow

class ArtistRepository(private val artistDao: ArtistDao) {
    fun getAllArtists(): Flow<List<ArtistEntity>> = artistDao.getAllArtists()
    suspend fun getArtistById(id: Int): ArtistEntity? = artistDao.getArtistById(id)
    fun getAllWorkshops(): Flow<List<WorkshopEntity>> = artistDao.getAllWorkshops()
    fun getWorkshopsByArtist(artistId: Int): Flow<List<WorkshopEntity>> = artistDao.getWorkshopsByArtist(artistId)
    suspend fun getWorkshopById(id: Int): WorkshopEntity? = artistDao.getWorkshopById(id)
    suspend fun insertArtists(artists: List<ArtistEntity>) = artistDao.insertArtists(artists)
    suspend fun insertWorkshops(workshops: List<WorkshopEntity>) = artistDao.insertWorkshops(workshops)
    suspend fun bookWorkshopSeat(workshopId: Int) = artistDao.bookWorkshopSeat(workshopId)
}
