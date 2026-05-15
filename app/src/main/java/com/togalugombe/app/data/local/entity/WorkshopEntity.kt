package com.togalugombe.app.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "workshops",
    foreignKeys = [
        ForeignKey(
            entity = ArtistEntity::class,
            parentColumns = ["id"],
            childColumns = ["artistId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["artistId"])]
)
data class WorkshopEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val artistId: Int,
    val title: String,
    val description: String,
    val date: String,
    val location: String,
    val price: Double,
    val seatsAvailable: Int
)
