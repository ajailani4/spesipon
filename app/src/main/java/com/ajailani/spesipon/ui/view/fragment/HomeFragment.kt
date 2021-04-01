package com.ajailani.spesipon.ui.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajailani.spesipon.databinding.FragmentHomeBinding
import com.ajailani.spesipon.ui.adapter.BrandHomeAdapter
import com.ajailani.spesipon.ui.viewmodel.HomeViewModel
import com.ajailani.spesipon.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by activityViewModels()
    private lateinit var brandHomeAdapter: BrandHomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(
            inflater, container, false
        )

        return binding.root
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    @ExperimentalCoroutinesApi
    private fun setupView() {
        // Setup brandAdapter and brandPhoneRv
        brandHomeAdapter = BrandHomeAdapter { brandSlug ->

        }

        binding.brandPhoneRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = brandHomeAdapter
        }

        // Get brands list and show it
        lifecycleScope.launch {
            homeViewModel.getBrands().collect { brands ->
                brandHomeAdapter.submitData(brands)
            }
        }
    }
}