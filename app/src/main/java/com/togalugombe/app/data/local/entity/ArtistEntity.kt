package com.togalugombe.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artists")
data class ArtistEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val bio: String,
    val location: String,
    val phone: String,
    val specialization: String,
    val imageRes: String
)
