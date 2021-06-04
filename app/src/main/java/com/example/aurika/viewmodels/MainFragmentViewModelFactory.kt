package com.example.aurika.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**class MainFragmentViewModelFactory(
    private val sleepNightKey: Long,
    private val dataSource: SleepDatabaseDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SleepQualityViewModel::class.java)) {
            return SleepQualityViewModel(sleepNightKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}**/