package com.thebrownfoxx.lithium.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.thebrownfoxx.lithium.data.model.CheckInEntity

@Database(entities = [CheckInEntity::class], version = 1, exportSchema = false)
abstract class LithiumDatabase : RoomDatabase() {

    abstract fun expressionDao(): CheckInDao

    companion object {
        @Volatile
        private var Instance: LithiumDatabase? = null

        fun getDatabase(context: Context): LithiumDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    LithiumDatabase::class.java,
                    "lithium_database",
                    )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}