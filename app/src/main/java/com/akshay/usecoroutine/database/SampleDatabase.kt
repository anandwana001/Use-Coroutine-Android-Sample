package com.akshay.usecoroutine.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by akshaynandwana on
 * 05, April, 2020
 **/

@Database(entities = [SampleTable::class], version = 1, exportSchema = false)
abstract class SampleDatabase : RoomDatabase() {

    abstract val sampleDao: SampleDao

    companion object {

        @Volatile
        private var INSTANCE: SampleDatabase? = null

        fun getInstance(context: Context): SampleDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            SampleDatabase::class.java,
                            "sample_database"
                        )
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}