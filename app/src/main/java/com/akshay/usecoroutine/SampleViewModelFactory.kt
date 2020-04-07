package com.akshay.usecoroutine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akshay.usecoroutine.database.SampleDao

/**
 * Created by akshaynandwana on
 * 05, April, 2020
 **/

class SampleViewModelFactory(
    private val dataSource: SampleDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SampleViewModel::class.java)) {
            return SampleViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}