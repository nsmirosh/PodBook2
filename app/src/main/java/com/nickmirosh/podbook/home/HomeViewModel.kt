package com.nickmirosh.podbook.home

import androidx.lifecycle.ViewModel
import com.example.dividendify.data.SearchRepository

class HomeViewModel : ViewModel() {

    fun performSearch() {
        val repo = SearchRepository()
        repo.performSearch("star wars")
    }
}