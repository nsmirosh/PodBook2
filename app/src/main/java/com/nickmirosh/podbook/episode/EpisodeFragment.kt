package com.nickmirosh.podbook.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.nickmirosh.podbook.R
import com.nickmirosh.podbook.databinding.FragmentEpisodeBinding
import com.nickmirosh.podbook.entity.Episode
import com.nickmirosh.podbook.utils.secondsToTimeString

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
        initViews()
    }

    private fun initViews() {
        binding.playBtn.setOnClickListener {
            playAudio(episodeViewModel.episode.value!!.audio)
        }
    }

    private fun playAudio(url: String) {
        val player = SimpleExoPlayer.Builder(this.requireActivity()).build()
        binding.videoView.setPlayer(player)
        val mediaItem = MediaItem.fromUri(url);
        player.setMediaItem(mediaItem);
        player.prepare()

    }

    private fun initializePlayer() {
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
            with(episode) {
                episodeNameTv.text = title
                lengthTv.text = secondsToTimeString(lengthInSecs.toInt())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}