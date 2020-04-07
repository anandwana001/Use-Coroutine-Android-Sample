package com.akshay.usecoroutine.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * Created by akshaynandwana on
 * 05, April, 2020
 **/

@Dao
interface SampleDao {

    @Insert
    suspend fun insert(night: SampleTable)

    @Query("SELECT * FROM sample_table")
    suspend fun getAllSampleData(): List<SampleTable>
}