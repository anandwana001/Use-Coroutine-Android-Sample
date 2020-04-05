package com.akshay.usecoroutine

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.akshay.usecoroutine.database.SampleDao
import com.akshay.usecoroutine.database.SampleTable
import kotlinx.coroutines.*

/**
 * Created by akshaynandwana on
 * 05, April, 2020
 **/

class SampleViewModel(
    val database: SampleDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    var sampleData = MutableLiveData<String>()

    init {
        initializeSampleData()
    }

    private fun initializeSampleData() {
        uiScope.launch {
            fillDatabase()
            delay(2000)
            sampleData.value = getSampleDataFromDatabase()
        }
    }

    private suspend fun getSampleDataFromDatabase(): String {
        return withContext(Dispatchers.IO) {
            database.getAllSampleData().toString()
        }
    }

    private suspend fun fillDatabase() {
        withContext(Dispatchers.IO) {
            database.insert(
                SampleTable(sampleData = "Sample pre fill data")
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}