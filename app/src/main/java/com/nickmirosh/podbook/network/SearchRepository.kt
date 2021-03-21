package com.example.dividendify.data

import com.nickmirosh.podbook.network.BaseRepository
import com.nickmirosh.podbook.network.LnResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
}