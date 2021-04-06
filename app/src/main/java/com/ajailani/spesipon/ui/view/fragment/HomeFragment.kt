package com.ajailani.spesipon.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajailani.spesipon.data.model.Brand
import com.ajailani.spesipon.data.model.Phone
import com.ajailani.spesipon.databinding.FragmentHomeBinding
import com.ajailani.spesipon.ui.adapter.FooterAdapter
import com.ajailani.spesipon.ui.adapter.home.BrandsHomeAdapter
import com.ajailani.spesipon.ui.viewmodel.HomeViewModel
import com.google.android.material.transition.MaterialSharedAxis
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var brandHomeAdapter: BrandsHomeAdapter

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
        // Setup brandsAdapter and brandsRv
        brandHomeAdapter = BrandsHomeAdapter({ brand ->
            navigateToPhones(brand)
        }, { brandSlug, phoneSlug ->
            navigateToPhoneSpecs(brandSlug, phoneSlug)
        })

        binding.brandsRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = brandHomeAdapter.withLoadStateFooter(
                footer = FooterAdapter()
            )

            brandHomeAdapter.addLoadStateListener { loadState ->
                if (loadState.refresh is LoadState.Loading) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

        // Get brands list and show it
        lifecycleScope.launch {
            homeViewModel.getBrandsHome().collect { brands ->
                brandHomeAdapter.submitData(brands)
            }
        }
    }

    private fun navigateToPhones(brand: Brand) {
        val direction = HomeFragmentDirections.actionHomeFragmentToPhonesFragment(brand.slug, brand.name)
        findNavController().navigate(direction)
    }

    private fun navigateToPhoneSpecs(brandSlug: String, phoneSlug: String) {
        val direction = HomeFragmentDirections.actionHomeFragmentToPhoneSpecsFragment(brandSlug, phoneSlug)
        findNavController().navigate(direction)
    }
}