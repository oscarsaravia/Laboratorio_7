package com.example.android.lab4v2.game

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.android.lab4v2.database.People
import com.example.android.lab4v2.database.ZventDatabaseDao
import kotlinx.coroutines.*

class ListViewModel(
    val database: ZventDatabaseDao,
    application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var persona = MutableLiveData<People>()







}

