package com.ajailani.spesipon.ui.adapter.phones

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ajailani.spesipon.data.model.Phone
import com.ajailani.spesipon.databinding.ItemPhoneBinding
import com.bumptech.glide.Glide

class PhonesAdapter(
    private val listener: (String) -> Unit
) : PagingDataAdapter<Phone, PhonesAdapter.ViewHolder>(DataDifferentiator) {
    private lateinit var binding: ItemPhoneBinding

    object DataDifferentiator : DiffUtil.ItemCallback<Phone>() {
        override fun areItemsTheSame(oldItem: Phone, newItem: Phone): Boolean {
            return oldItem.slug == newItem.slug
        }

        override fun areContentsTheSame(oldItem: Phone, newItem: Phone): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(private val binding: ItemPhoneBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(phone: Phone, listener: (String) -> Unit) {
            binding.apply {
                Glide.with(image.context)
                    .load(phone.image)
                    .into(image)

                name.text = phone.name

                root.setOnClickListener {
                    listener(phone.slug)
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