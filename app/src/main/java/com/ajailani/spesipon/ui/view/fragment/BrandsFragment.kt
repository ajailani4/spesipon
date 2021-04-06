package com.ajailani.spesipon.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajailani.spesipon.databinding.FragmentBrandsBinding
import com.ajailani.spesipon.ui.adapter.FooterAdapter
import com.ajailani.spesipon.ui.adapter.brands.BrandsAdapter
import com.ajailani.spesipon.ui.viewmodel.BrandsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BrandsFragment : Fragment() {
    private lateinit var binding: FragmentBrandsBinding
    private val brandsViewModel: BrandsViewModel by viewModels()
    private lateinit var brandsAdapter: BrandsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentBrandsBinding.inflate(
            inflater, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {
        // Setup brandsAdapter and brandsRv
        brandsAdapter = BrandsAdapter()

        brandsAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                binding.apply {
                    progressBar.visibility = View.VISIBLE
                    brandsRv.visibility = View.GONE
                }
            } else {
                binding.apply {
                    progressBar.visibility = View.GONE
                    brandsRv.visibility = View.VISIBLE
                }
            }
        }

        binding.brandsRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = brandsAdapter.withLoadStateFooter(
                footer = FooterAdapter()
            )
        }

        // Get brands list and show it
        lifecycleScope.launch {
            brandsViewModel.getBrands().collect { brands ->
                brandsAdapter.submitData(brands)
            }
        }
    }
}