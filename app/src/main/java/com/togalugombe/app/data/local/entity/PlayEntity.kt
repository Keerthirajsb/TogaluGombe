package com.togalugombe.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plays")
data class PlayEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titleEn: String,
    val titleKn: String,
    val descriptionEn: String,
    val descriptionKn: String,
    val thumbnailRes: String,
    val sceneCount: Int
)
