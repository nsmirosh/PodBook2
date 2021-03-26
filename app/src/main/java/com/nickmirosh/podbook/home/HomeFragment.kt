package com.nickmirosh.podbook.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nickmirosh.podbook.R
import com.nickmirosh.podbook.databinding.FragmentHomeBinding
import com.nickmirosh.podbook.network.SearchResult

class HomeFragment : Fragment(R.layout.fragment_home) {

    val homeViewModel: HomeViewModel by viewModels()

    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater)
        startObservingLiveData()
        initListeners()
        setUpViews()
    }

    private fun setUpViews() {
        with(binding.searchResultRV) {
            adapter = HomeAdapter()
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
        (binding.searchResultRV.adapter as HomeAdapter).setData(data)
    }

    private fun initListeners() {
        with(binding) {
            searchBtn.setOnClickListener {
                homeViewModel.performSearch(searchET.text.toString())
            }
        }
    }
}