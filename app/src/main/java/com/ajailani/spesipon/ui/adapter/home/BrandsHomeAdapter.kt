package com.ajailani.spesipon.ui.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ajailani.spesipon.data.model.Brand
import com.ajailani.spesipon.databinding.ItemBrandHomeBinding

class BrandsHomeAdapter(
    private val moreListener: (Brand) -> Unit,
    private val phoneListener: (String, String) -> Unit
) : PagingDataAdapter<Brand, BrandsHomeAdapter.ViewHolder>(DataDifferentiator) {
    private lateinit var binding: ItemBrandHomeBinding
    private lateinit var phonesHomeAdapter: PhonesHomeAdapter
    private val rvViewPool = RecyclerView.RecycledViewPool()

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
        fun bind(brand: Brand, moreListener: (Brand) -> Unit) {
            binding.apply {
                name.text = brand.name

                moreIv.setOnClickListener {
                    moreListener(brand)
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
            holder.bind(brand, moreListener)

            // Setup phonesHomeAdapter and phonesHomeRv
            phonesHomeAdapter = PhonesHomeAdapter(brand.phonesList) { phoneSlug ->
                phoneListener(brand.slug, phoneSlug)
            }

            binding.phonesHomeRv.apply {
                layoutManager = LinearLayoutManager(
                    context, LinearLayoutManager.HORIZONTAL, false
                )
                adapter = phonesHomeAdapter
                setRecycledViewPool(rvViewPool)
            }
        }
    }
}