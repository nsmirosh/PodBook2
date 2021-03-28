package com.nickmirosh.podbook.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.nickmirosh.podbook.R
import com.nickmirosh.podbook.databinding.FragmentEpisodeBinding
import com.nickmirosh.podbook.entity.Episode

class EpisodeFragment : Fragment(R.layout.fragment_episode) {

    val episodeViewModel: EpisodeViewModel by viewModels()

    val args: EpisodeFragmentArgs by navArgs()

    private var _binding: FragmentEpisodeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEpisodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startObservingLiveData()
        getEpisodeData()
    }

    private fun getEpisodeData() {
        episodeViewModel.getEpisodeData(args.episodeId)
    }

    private fun startObservingLiveData() {
        episodeViewModel.episode.observe(
            viewLifecycleOwner,
            { onDataReceived(it) }
        )
    }

    private fun onDataReceived(episode: Episode) {
        with(binding) {
            episodeNameTv.text = episode.title
            lengthTv.text = episode.lengthInSecs
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}