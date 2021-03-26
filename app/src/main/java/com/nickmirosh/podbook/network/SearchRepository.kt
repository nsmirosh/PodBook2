package com.example.dividendify.data

import com.nickmirosh.podbook.network.BaseRepository
import com.nickmirosh.podbook.network.LnResponse
import com.nickmirosh.podbook.network.SearchResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext


class SearchRepository : BaseRepository() {

    val searchService = buildRetrofit().create(SearchService::class.java)

    suspend fun performCoroutineSearch(query: String): List<SearchResult> {

        var searchResults: List<SearchResult>

        withContext(Dispatchers.IO) {
            val dogBreedListDeferred = async { searchService.performSearch(query)?.execute() }
            val searchResponse = dogBreedListDeferred.await()

            val actualResponse: LnResponse? = searchResponse?.body()
            searchResults = actualResponse!!.results
        }
        return searchResults
    }
}