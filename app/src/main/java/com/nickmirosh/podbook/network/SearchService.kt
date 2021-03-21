package com.example.dividendify.data

import com.nickmirosh.podbook.network.LnResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("/search")
    fun performSearch(
        @Query("q") query: String
    ): Call<LnResponse>?
}