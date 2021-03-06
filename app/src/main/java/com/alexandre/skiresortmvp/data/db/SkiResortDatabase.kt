package com.alexandre.skiresortmvp.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.alexandre.skiresortmvp.data.db.model.SkiResort

/**
 * Database to store all ski resorts
 */
@Database(
        entities = [SkiResort::class],
        version = 2,
        exportSchema = false
)
abstract class SkiResortDatabase : RoomDatabase() {

    abstract fun skiResortDao(): SkiResortDao

    companion object {

        @Volatile
        private var INSTANCE: SkiResortDatabase? = null

        fun getInstance(context: Context): SkiResortDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE
                            ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        SkiResortDatabase::class.java, "skiResorts.db")
                        .fallbackToDestructiveMigration()
                        .build()
    }
}