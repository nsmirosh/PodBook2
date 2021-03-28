package com.nickmirosh.podbook.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dividendify.data.ListenNotesRepo
import com.nickmirosh.podbook.entity.Episode
import kotlinx.coroutines.launch

class EpisodeViewModel : ViewModel() {

    private val _episode: MutableLiveData<Episode> = MutableLiveData()
    val episode: LiveData<Episode> get() = _episode

    fun getEpisodeData(episodeId: String) {
        val repo = ListenNotesRepo()
        viewModelScope.launch {
            _episode.value = repo.getEpisodeData(episodeId)
        }
    }
}