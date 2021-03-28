package com.nickmirosh.podbook.network

import com.google.gson.annotations.SerializedName
import com.nickmirosh.podbook.entity.Podcast

data class Episode(
    val id: String,
    val thumbnail: String,
    @SerializedName("title_original")
    val title: String,
    val audio: String,
    val podcast: Podcast
)
