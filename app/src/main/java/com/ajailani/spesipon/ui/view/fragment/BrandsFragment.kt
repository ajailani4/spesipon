package com.ajailani.spesipon.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ajailani.spesipon.databinding.FragmentBrandsBinding

class BrandsFragment : Fragment() {
    private lateinit var binding: FragmentBrandsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentBrandsBinding.inflate(
            inflater, container, false
        )

        return binding.root
    }
}