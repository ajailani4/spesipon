package com.ajailani.spesipon.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ajailani.spesipon.data.model.brand.Brand
import com.ajailani.spesipon.data.model.phone.Phone
import com.ajailani.spesipon.databinding.ItemBrandHomeBinding
import com.ajailani.spesipon.utils.Resource
import com.ajailani.spesipon.utils.Status

class BrandHomeAdapter(
    private val listener: (String) -> Unit
): PagingDataAdapter<Brand, BrandHomeAdapter.ViewHolder>(DataDifferentiator) {
    private lateinit var binding: ItemBrandHomeBinding
    private lateinit var phonesHomeAdapter: PhonesHomeAdapter

    object DataDifferentiator : DiffUtil.ItemCallback<Brand>() {
        override fun areItemsTheSame(oldItem: Brand, newItem: Brand): Boolean {
            return oldItem.slug == newItem.slug
        }

        override fun areContentsTheSame(oldItem: Brand, newItem: Brand): Boolean {
            return oldItem == oldItem
        }
    }

    class ViewHolder(private val binding: ItemBrandHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(brand: Brand, listener: (String) -> Unit) {
            binding.apply {
                name.text = brand.name

                moreIv.setOnClickListener {
                    listener(brand.slug)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemBrandHomeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { brand ->
            holder.bind(brand, listener)

            // Setup phonesHomeAdapter and phonesHomeRv
            phonesHomeAdapter = PhonesHomeAdapter(brand.phonesList)

            binding.phonesHomeRv.apply {
                layoutManager = LinearLayoutManager(
                    context, LinearLayoutManager.HORIZONTAL, false
                )

                adapter = phonesHomeAdapter
            }
        }
    }
}