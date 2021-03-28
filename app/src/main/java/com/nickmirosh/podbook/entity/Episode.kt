package com.nickmirosh.podbook.entity

import com.google.gson.annotations.SerializedName
import com.nickmirosh.podbook.entity.Podcast

data class Episode(
    val id: String,
    val thumbnail: String,
    val title: String,
    @SerializedName("audio_length_sec")
    val lengthInSecs: String,
    val audio: String,
    val podcast: Podcast
)
