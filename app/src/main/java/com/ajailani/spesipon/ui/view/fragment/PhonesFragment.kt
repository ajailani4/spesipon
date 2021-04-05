package com.ajailani.spesipon.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.ajailani.spesipon.R
import com.ajailani.spesipon.data.model.phone.Phone
import com.ajailani.spesipon.databinding.FragmentPhonesBinding
import com.ajailani.spesipon.ui.adapter.phones.PhonesAdapter
import com.ajailani.spesipon.ui.adapter.FooterAdapter
import com.ajailani.spesipon.ui.viewmodel.PhonesBrandViewModel
import com.google.android.material.transition.MaterialSharedAxis
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhonesFragment : Fragment() {
    private lateinit var binding: FragmentPhonesBinding
    private val args: PhonesFragmentArgs by navArgs()
    private val phonesBrandViewModel: PhonesBrandViewModel by viewModels()
    private lateinit var phonesAdapter: PhonesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentPhonesBinding.inflate(
            inflater, container, false
        )

        return binding.root
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        setupView()
    }

    @ExperimentalCoroutinesApi
    private fun setupView() {
        binding.toolbar.title = args.brandName

        // Setup phonesAdapter and phonesRv
        phonesAdapter = PhonesAdapter { phone ->
            navigateToPhoneSpecs(args.brandSlug, phone)
        }

        binding.phonesRv.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = phonesAdapter.withLoadStateFooter(
                footer = FooterAdapter()
            )

            phonesAdapter.addLoadStateListener { loadState ->
                if (loadState.refresh is LoadState.Loading) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

        // Get phones list and show it
        lifecycleScope.launch {
            phonesBrandViewModel.getPhones(args.brandSlug).collect { phones ->
                phonesAdapter.submitData(phones)
            }
        }
    }

    private fun navigateToPhoneSpecs(brandSlug: String, phone: Phone?) {
        // Setup transition animation from HomeFragment to PhoneSpecsFragment
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
            duration = 300
        }

        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
            duration = 300
        }

        val direction = PhonesFragmentDirections.actionPhonesFragmentToPhoneSpecsFragment(brandSlug, phone?.slug!!)
        findNavController().navigate(direction)
    }
}