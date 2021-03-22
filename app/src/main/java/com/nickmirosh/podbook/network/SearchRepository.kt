package com.example.dividendify.data

import com.nickmirosh.podbook.network.BaseRepository
import com.nickmirosh.podbook.network.LnResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.io.IOException


class SearchRepository : BaseRepository() {

    val newsService = buildRetrofit().create(SearchService::class.java)

    fun performSearch(query: String) {
        newsService.performSearch(query)?.enqueue(object : Callback<LnResponse> {
            override fun onResponse(
                call: Call<LnResponse?>?,
                response: Response<LnResponse?>?
            ) {
            }

            override fun onFailure(call: Call<LnResponse?>?, t: Throwable?) {

            }
        })
    }

    suspend fun performCoroutineSearch(query: String){

        withContext(Dispatchers.IO) {
            val dogBreedListDeferred = async { newsService.performSearch(query)?.execute() }
            val searchResponse = dogBreedListDeferred.await()

            val actualResponse: LnResponse? = searchResponse?.body()

            val result = actualResponse?.results!![0].id
            Timber.d("result = $result")
        }
    }
}