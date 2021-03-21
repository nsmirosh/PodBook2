package com.nickmirosh.podbook.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nickmirosh.podbook.R

class HomeFragment : Fragment(R.layout.fragment_home) {

    val homeViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startObservingLiveData()
        initListeners()
    }


    private fun startObservingLiveData() {


    }

    private fun initListeners() {
        homeViewModel.performSearch()
    }
}