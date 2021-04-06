package com.ajailani.spesipon.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ajailani.spesipon.databinding.FragmentPhoneSearchBinding
import com.google.android.material.transition.MaterialSharedAxis

class PhoneSearchFragment : Fragment() {
    private lateinit var binding: FragmentPhoneSearchBinding

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

    private fun setupView() {
        // Setup EditText to become search bar
        binding.apply {
            phoneSearchInput.addTextChangedListener { text ->
                if (text!!.isNotEmpty()) {
                    closeSearchIv.visibility = View.VISIBLE
                } else {
                    closeSearchIv.visibility = View.GONE
                }
            }
        }
    }
}