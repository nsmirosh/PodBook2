package com.example.dividendify.data

import com.nickmirosh.podbook.network.BaseRepository


class SearchRepository : BaseRepository() {

    val newsService = buildRetrofit().create(SearchService::class.java)

    fun performSearch(query: String) {
        newsService.performSearch(query)
    }
}