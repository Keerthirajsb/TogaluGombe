package com.togalugombe.app.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.togalugombe.app.data.local.dao.*
import com.togalugombe.app.data.local.entity.*

@Database(
    entities = [
        PlayEntity::class,
        SceneEntity::class,
        PuppetEntity::class,
        ArtistEntity::class,
        WorkshopEntity::class,
        HistoryVideoEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playDao(): PlayDao
    abstract fun puppetDao(): PuppetDao
    abstract fun artistDao(): ArtistDao
    abstract fun historyVideoDao(): HistoryVideoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "togalu_gombe_database"
                ).fallbackToDestructiveMigration()
                    .build().also { INSTANCE = it }
            }
        }
    }
}
