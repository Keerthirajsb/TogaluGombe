package com.togalugombe.app

import android.app.Application
import com.togalugombe.app.data.local.AppDatabase

class TogaluGombeApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
}
