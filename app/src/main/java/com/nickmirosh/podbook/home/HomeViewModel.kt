package com.nickmirosh.podbook.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dividendify.data.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    fun performSearch() {
        val repo = SearchRepository()

        viewModelScope.launch {
            repo.performCoroutineSearch("star wars")
        }

    }
}