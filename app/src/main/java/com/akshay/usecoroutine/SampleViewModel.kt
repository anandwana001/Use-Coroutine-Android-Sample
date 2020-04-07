package com.akshay.usecoroutine

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akshay.usecoroutine.database.SampleDao
import com.akshay.usecoroutine.database.SampleTable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by akshaynandwana on
 * 05, April, 2020
 **/

class SampleViewModel(
    val database: SampleDao
) : ViewModel() {

    var sampleData = MutableLiveData<String>()

    init {
        initializeSampleData()
    }

    private fun initializeSampleData() {
        viewModelScope.launch {
            fillDatabase()
            delay(2000)
            sampleData.value = getSampleDataFromDatabase()
        }
    }

    private suspend fun getSampleDataFromDatabase(): String {
        return database.getAllSampleData().toString()
    }

    private suspend fun fillDatabase() {
        database.insert(
            SampleTable(sampleData = "Sample pre fill data")
        )
    }
}