package com.akshay.usecoroutine.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by akshaynandwana on
 * 05, April, 2020
 **/

@Entity(tableName = "sample_table")
data class SampleTable(
    @PrimaryKey(autoGenerate = true)
    var sampleId: Int = 0,

    @ColumnInfo(name = "sample_data")
    val sampleData: String
)