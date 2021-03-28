package com.nickmirosh.podbook.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dividendify.data.ListenNotesRepo
import com.nickmirosh.podbook.entity.Episode
import com.nickmirosh.podbook.entity.SearchResult
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _searchResults: MutableLiveData<List<SearchResult>> = MutableLiveData()
    val searchResults: LiveData<List<SearchResult>> get() = _searchResults

    fun performSearch(query: String) {
        val repo = ListenNotesRepo()
        viewModelScope.launch {
            _searchResults.value = repo.performCoroutineSearch(query)
        }
    }
}