package com.ajailani.spesipon.ui.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.ajailani.spesipon.data.model.PhoneSearch
import com.ajailani.spesipon.databinding.FragmentPhoneSearchBinding
import com.ajailani.spesipon.ui.adapter.FooterAdapter
import com.ajailani.spesipon.ui.adapter.phonesearch.PhoneSearchAdapter
import com.ajailani.spesipon.ui.viewmodel.PhoneSearchViewModel
import com.google.android.material.transition.MaterialSharedAxis
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhoneSearchFragment : Fragment() {
    private lateinit var binding: FragmentPhoneSearchBinding
    private val phoneSearchViewModel: PhoneSearchViewModel by viewModels()
    private lateinit var phoneSearchAdapter: PhoneSearchAdapter

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
        binding = FragmentPhoneSearchBinding.inflate(
            inflater, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        setupView()
    }

    private fun setupSearchBar() {
        binding.apply {
            // Automatically open keyboard when this fragment is opened
            phoneSearchInput.requestFocus()
            val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.toggleSoftInput(
                InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY
            )

            // Setup phoneSearchInput to become search bar
            phoneSearchInput.addTextChangedListener { text ->
                if (text!!.isNotEmpty()) {
                    closeSearchIv.apply {
                        visibility = View.VISIBLE

                        setOnClickListener {
                            phoneSearchInput.text?.clear()
                        }
                    }

                } else {
                    closeSearchIv.visibility = View.GONE
                }
            }

            // Get query from phoneSearchInput and show the result
            phoneSearchInput.setOnEditorActionListener { v, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    getPhoneSearch(v.text.toString())

                    // Hide keyboard
                    inputMethodManager.hideSoftInputFromWindow(
                        v.windowToken, 0
                    )

                    return@setOnEditorActionListener true
                }

                return@setOnEditorActionListener false
            }
        }
    }

    private fun setupView() {
        setupSearchBar()

        // Setup phoneSearchAdapter and phonesRv
        phoneSearchAdapter = PhoneSearchAdapter { phone ->
            navigateToPhoneSpecs(phone)
        }

        phoneSearchAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                binding.apply {
                    progressBar.visibility = View.VISIBLE
                    phonesRv.visibility = View.GONE
                }
            } else {
                binding.apply {
                    progressBar.visibility = View.GONE
                    phonesRv.visibility = View.VISIBLE
                }
            }
        }

        binding.phonesRv.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = phoneSearchAdapter.withLoadStateFooter(
                footer = FooterAdapter()
            )
        }
    }

    private fun getPhoneSearch(phoneQuery: String) {
        lifecycleScope.launch {
            phoneSearchViewModel.getPhoneSearch(phoneQuery).collect { phones ->
                phoneSearchAdapter.submitData(phones)
            }
        }
    }

    private fun navigateToPhoneSpecs(phone: PhoneSearch) {
        val direction = PhoneSearchFragmentDirections.actionPhoneSearchFragmentToPhoneSpecsFragment(phone.brandSlug, phone.slug)
        findNavController().navigate(direction)
    }
}