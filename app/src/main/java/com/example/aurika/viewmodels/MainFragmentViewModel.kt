package com.example.aurika.viewmodels

import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.*
import com.example.aurika.network.BooksApi
import com.example.aurika.network.asDomainModel
import com.example.aurika.ui.BookDomain
import kotlinx.coroutines.launch


class MainFragmentViewModel : ViewModel() {


    private val _liveList = MutableLiveData<List<BookDomain>>()
    val livelist: LiveData<List<BookDomain>>
        get() = _liveList

    private val _progressBarVisibility = MutableLiveData<Int>()
    val progressBarVisibilty: LiveData<Int>
        get() = _progressBarVisibility

    private val _readerRecommendationVisibility = MutableLiveData<Int>()
    val readerRecommendationVisibilty: LiveData<Int>
        get()=_readerRecommendationVisibility

    init {
        _progressBarVisibility.value = ProgressBar.INVISIBLE
        _readerRecommendationVisibility.value = TextView.VISIBLE
    }




    fun searchBook(string: String) {

        _liveList.value = null


        _progressBarVisibility.value = ProgressBar.VISIBLE
        _readerRecommendationVisibility.value = TextView.INVISIBLE

        viewModelScope.launch {

            try {
                _liveList.value = BooksApi.retrofitService.getBooks(string).asDomainModel()
                _progressBarVisibility.value = ProgressBar.INVISIBLE

            } catch (e: Exception) {
                Log.d("aare", "ERROR  " + e.message)
            }
        }
    }





}

