package com.example.gobeyond.ui.data

import android.content.Context
import androidx.room.Room
import com.example.gobeyond.ui.data.local.GoBeyondDatabase

object AppDatabaseProvider {
    fun createDatabase(context: Context): GoBeyondDatabase {
        return Room.databaseBuilder(
            context,
            GoBeyondDatabase::class.java,
            "gobeyond.db"
        ).build()
    }
}