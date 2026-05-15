package com.togalugombe.app.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "scenes",
    foreignKeys = [
        ForeignKey(
            entity = PlayEntity::class,
            parentColumns = ["id"],
            childColumns = ["playId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["playId"])]
)
data class SceneEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val playId: Int,
    val sceneNumber: Int,
    val titleEn: String,
    val titleKn: String,
    val summaryEn: String,
    val summaryKn: String,
    val characters: String,
    val imageRes: String
)
