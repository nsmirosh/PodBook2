package com.example.dividendify.data

import com.nickmirosh.podbook.network.BaseRepository
import com.nickmirosh.podbook.entity.LnResponse
import com.nickmirosh.podbook.entity.Episode
import com.nickmirosh.podbook.entity.SearchResult
import com.nickmirosh.podbook.network.api.DirectoryApi
import com.nickmirosh.podbook.network.api.SearchApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext


class ListenNotesRepo : BaseRepository() {

    val searchApi = buildRetrofit().create(SearchApi::class.java)

    val directoryApi = buildRetrofit().create(DirectoryApi::class.java)

    suspend fun performCoroutineSearch(query: String): List<SearchResult> = withContext(Dispatchers.IO) {
        val searchDeffered = async { searchApi.performSearch(query).execute() }
        val searchResponse = searchDeffered.await()
        val actualResponse: LnResponse = searchResponse.body()!!
        actualResponse.results
    }

    suspend fun getEpisodeData(id: String): Episode = withContext(Dispatchers.IO) {
        val getEpisodeDeffered = async { directoryApi.getEpisodeBy(id)?.execute() }
        val searchResponse = getEpisodeDeffered.await()
        searchResponse?.body()!!
    }
}