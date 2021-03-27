package com.nickmirosh.podbook.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nickmirosh.podbook.R
import com.nickmirosh.podbook.databinding.FragmentHomeBinding
import com.nickmirosh.podbook.network.SearchResult
import com.nickmirosh.podbook.utils.gone
import com.nickmirosh.podbook.utils.visible

class HomeFragment : Fragment(R.layout.fragment_home), HomeAdapter.InteractionListener {

    val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startObservingLiveData()
        initListeners()
        setUpViews()
    }

    private fun setUpViews() {
        with(binding.searchResultRV) {
            adapter = HomeAdapter(this@HomeFragment)
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun startObservingLiveData() {
        homeViewModel.searchResults.observe(
            viewLifecycleOwner,
            { onDataReceived(it) }
        )
    }

    private fun onDataReceived(data: List<SearchResult>) {
        with(binding) {
            progressBar.gone()
            searchResultRV.visible()
            (searchResultRV.adapter as HomeAdapter).setData(data)
        }
    }

    private fun initListeners() {
        with(binding) {
            searchBtn.setOnClickListener {
                progressBar.visible()
                searchResultRV.gone()
                homeViewModel.performSearch(searchET.text.toString())
            }
        }
    }

    override fun onEpisodeClick(episodeId: String) {
        view?.findNavController()?.navigate(HomeFragmentDirections.actionHomeFragmentToEpisodeFragment(episodeId))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}