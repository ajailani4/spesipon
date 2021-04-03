package com.ajailani.spesipon.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajailani.spesipon.data.model.brand.Brand
import com.ajailani.spesipon.databinding.FragmentHomeBinding
import com.ajailani.spesipon.ui.adapter.FooterAdapter
import com.ajailani.spesipon.ui.adapter.home.BrandHomeAdapter
import com.ajailani.spesipon.ui.viewmodel.HomeViewModel
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
        brandHomeAdapter = BrandHomeAdapter { brand ->
            navigateToPhonesBrand(brand)
        }

        binding.brandPhoneRv.apply {
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
            homeViewModel.getBrands().collect { brands ->
                brandHomeAdapter.submitData(brands)
            }
        }
    }

    private fun navigateToPhonesBrand(brand: Brand) {
        val direction = HomeFragmentDirections.actionHomeFragmentToPhonesFragment(brand.slug, brand.name)
        findNavController().navigate(direction)
    }
}