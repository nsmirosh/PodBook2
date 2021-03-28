package com.nickmirosh.podbook.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dividendify.data.ListenNotesRepo
import com.nickmirosh.podbook.network.Episode
import kotlinx.coroutines.launch

class EpisodeViewModel : ViewModel() {

    private val _searchResults: MutableLiveData<Episode> = MutableLiveData()
    val searchResults: LiveData<Episode> get() = _searchResults

    fun getEpisodeData(episodeId: String) {
        val repo = ListenNotesRepo()
        viewModelScope.launch {
            _searchResults.value = repo.getEpisodeData(episodeId)
        }
    }
}