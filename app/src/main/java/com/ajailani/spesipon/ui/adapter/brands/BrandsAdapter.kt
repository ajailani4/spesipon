package com.ajailani.spesipon.ui.adapter.brands

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ajailani.spesipon.R
import com.ajailani.spesipon.data.model.Brand
import com.ajailani.spesipon.databinding.ItemBrandBinding

class BrandsAdapter : PagingDataAdapter<Brand, BrandsAdapter.ViewHolder>(DataDifferentiator) {
    private lateinit var binding: ItemBrandBinding

    object DataDifferentiator : DiffUtil.ItemCallback<Brand>() {
        override fun areItemsTheSame(oldItem: Brand, newItem: Brand): Boolean {
            return oldItem.slug == newItem.slug
        }

        override fun areContentsTheSame(oldItem: Brand, newItem: Brand): Boolean {
            return oldItem == oldItem
        }
    }

    class ViewHolder(private val binding: ItemBrandBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(brand: Brand) {
            binding.apply {
                name.text = brand.name
                countDevices.text = root.context.getString(R.string.count_devices, brand.countDevices)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemBrandBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { brand -> holder.bind(brand) }
    }
}