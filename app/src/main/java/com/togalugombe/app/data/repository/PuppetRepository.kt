package com.togalugombe.app.data.repository

import com.togalugombe.app.data.local.dao.PuppetDao
import com.togalugombe.app.data.local.entity.PuppetEntity
import kotlinx.coroutines.flow.Flow

class PuppetRepository(private val puppetDao: PuppetDao) {
    fun getAllPuppets(): Flow<List<PuppetEntity>> = puppetDao.getAllPuppets()
    suspend fun getPuppetById(id: Int): PuppetEntity? = puppetDao.getPuppetById(id)
    fun getPuppetsByType(type: String): Flow<List<PuppetEntity>> = puppetDao.getPuppetsByType(type)
    fun getPuppetsByPlay(playName: String): Flow<List<PuppetEntity>> = puppetDao.getPuppetsByPlay(playName)
    fun searchPuppets(query: String): Flow<List<PuppetEntity>> = puppetDao.searchPuppets(query)
    suspend fun insertPuppets(puppets: List<PuppetEntity>) = puppetDao.insertPuppets(puppets)
}
