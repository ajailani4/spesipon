package com.ajailani.spesipon.ui.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajailani.spesipon.data.model.Phone
import com.ajailani.spesipon.databinding.ItemPhoneHomeBinding
import com.bumptech.glide.Glide

class PhonesHomeAdapter(
    private val phonesHomeList: List<Phone>?,
    private val listener: (String) -> Unit
) : RecyclerView.Adapter<PhonesHomeAdapter.ViewHolder>() {
    private lateinit var binding: ItemPhoneHomeBinding

    class ViewHolder(private val binding: ItemPhoneHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(phone: Phone?, listener: (String) -> Unit) {
            binding.apply {
                Glide.with(image.context)
                    .load(phone?.image)
                    .into(image)

                name.text = phone?.name

                root.setOnClickListener {
                    listener(phone!!.slug)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemPhoneHomeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(phonesHomeList?.get(position), listener)
    }

    override fun getItemCount() = phonesHomeList!!.size
}