package com.togalugombe.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "puppets")
data class PuppetEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nameEn: String,
    val nameKn: String,
    val descriptionEn: String,
    val descriptionKn: String,
    val powersEn: String,
    val powersKn: String,
    val symbolismEn: String,
    val symbolismKn: String,
    val imageRes: String,
    val playName: String,
    val characterType: String
)
