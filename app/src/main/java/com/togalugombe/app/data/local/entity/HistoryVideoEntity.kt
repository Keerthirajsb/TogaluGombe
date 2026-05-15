package com.togalugombe.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_videos")
data class HistoryVideoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titleEn: String,
    val titleKn: String,
    val descriptionEn: String,
    val descriptionKn: String,
    val videoUri: String,
    val thumbnailRes: String,
    val category: String,
    val durationSeconds: Int
)
