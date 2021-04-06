package com.ajailani.spesipon.ui.adapter.phonesearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ajailani.spesipon.data.model.Phone
import com.ajailani.spesipon.data.model.PhoneSearch
import com.ajailani.spesipon.databinding.ItemPhoneBinding
import com.bumptech.glide.Glide

class PhoneSearchAdapter(
    private val listener: (PhoneSearch) -> Unit
) : PagingDataAdapter<PhoneSearch, PhoneSearchAdapter.ViewHolder>(DataDifferentiator) {
    private lateinit var binding: ItemPhoneBinding

    object DataDifferentiator : DiffUtil.ItemCallback<PhoneSearch>() {
        override fun areItemsTheSame(oldItem: PhoneSearch, newItem: PhoneSearch): Boolean {
            return oldItem.slug == newItem.slug
        }

        override fun areContentsTheSame(oldItem: PhoneSearch, newItem: PhoneSearch): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(private val binding: ItemPhoneBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(phone: PhoneSearch, listener: (PhoneSearch) -> Unit) {
            binding.apply {
                Glide.with(image.context)
                    .load(phone.image)
                    .into(image)

                name.text = phone.name

                root.setOnClickListener {
                    listener(phone)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemPhoneBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { phone -> holder.bind(phone, listener) }
    }
}