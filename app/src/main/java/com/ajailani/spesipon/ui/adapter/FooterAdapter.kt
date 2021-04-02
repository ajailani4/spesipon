package com.ajailani.spesipon.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ajailani.spesipon.databinding.FooterProgressBarBinding

class FooterAdapter : LoadStateAdapter<FooterAdapter.ViewHolder>() {
    private lateinit var binding: FooterProgressBarBinding

    class ViewHolder(binding: FooterProgressBarBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        binding = FooterProgressBarBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        if (loadState is LoadState.Loading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}