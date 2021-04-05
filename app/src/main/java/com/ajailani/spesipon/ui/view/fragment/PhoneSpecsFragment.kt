package com.ajailani.spesipon.ui.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajailani.spesipon.databinding.FragmentPhoneSpecsBinding
import com.ajailani.spesipon.ui.adapter.phonespecs.PhoneSpecsAdapter
import com.ajailani.spesipon.ui.viewmodel.PhoneSpecsViewModel
import com.ajailani.spesipon.utils.Status
import com.bumptech.glide.Glide
import com.google.android.material.transition.MaterialSharedAxis
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhoneSpecsFragment : Fragment() {
    private lateinit var binding: FragmentPhoneSpecsBinding
    private val phoneSpecsViewModel: PhoneSpecsViewModel by viewModels()
    private lateinit var phoneSpecsAdapter: PhoneSpecsAdapter
    private val args: PhoneSpecsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup transition animation
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
            duration = 300
        }

        returnTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
            duration = 300
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentPhoneSpecsBinding.inflate(
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

    private fun setupLoadingUI() {
        binding.apply {
            progressBar.visibility = View.VISIBLE
            image.visibility = View.GONE
            name.visibility = View.GONE
            phoneSpecsRv.visibility = View.GONE
        }
    }

    private fun setupLoadedUI() {
        binding.apply {
            progressBar.visibility = View.GONE
            image.visibility = View.VISIBLE
            name.visibility = View.VISIBLE
            phoneSpecsRv.visibility = View.VISIBLE
        }
    }

    @ExperimentalCoroutinesApi
    private fun setupView() {
        lifecycleScope.launch {
            phoneSpecsViewModel.fetchPhoneSpecsData(args.brandSlug, args.phoneSlug)
            val phoneSpecsData = phoneSpecsViewModel.getPhoneSpecsData()

            phoneSpecsData.collect {
                when (it.status) {
                    Status.LOADING -> {
                        setupLoadingUI()
                    }

                    Status.SUCCESS -> {
                        // Setup phoneSpecsAdapter and phoneSpecsTitleRv
                        phoneSpecsAdapter = it.data?.let { phoneSpecsData ->
                            PhoneSpecsAdapter(phoneSpecsData.specifications)
                        }!!

                        binding.apply {
                            Glide.with(image.context)
                                .load(it.data.image)
                                .into(image)

                            name.text = it.data.name

                            // Show phone specs
                            phoneSpecsRv.apply {
                                layoutManager = LinearLayoutManager(context)
                                adapter = phoneSpecsAdapter
                            }
                        }

                        setupLoadedUI()
                    }

                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}