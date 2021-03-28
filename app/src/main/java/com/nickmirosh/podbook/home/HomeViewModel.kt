package com.nickmirosh.podbook.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dividendify.data.ListenNotesRepo
import com.nickmirosh.podbook.network.Episode
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _searchResults: MutableLiveData<List<Episode>> = MutableLiveData()
    val searchResults: LiveData<List<Episode>> get() = _searchResults

    fun performSearch(query: String) {
        val repo = ListenNotesRepo()
        viewModelScope.launch {
            _searchResults.value = repo.performCoroutineSearch(query)
        }
    }
}