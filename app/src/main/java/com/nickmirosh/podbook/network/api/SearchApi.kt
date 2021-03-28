package com.nickmirosh.podbook.network.api

import com.nickmirosh.podbook.network.LnResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("search")
    fun performSearch(
        @Query("q") query: String
    ): Call<LnResponse>

}