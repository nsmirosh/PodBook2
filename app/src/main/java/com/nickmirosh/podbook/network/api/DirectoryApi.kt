package com.nickmirosh.podbook.network.api

import com.nickmirosh.podbook.entity.Episode
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DirectoryApi {

    @GET("episodes/{id}")
    fun getEpisodeBy(
        @Path("id") episodeId: String
    ): Call<Episode>?

}