package com.ajailani.spesipon.ui.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajailani.spesipon.data.model.phone.Phone
import com.ajailani.spesipon.databinding.ItemPhoneHomeBinding
import com.bumptech.glide.Glide

class PhonesHomeAdapter(
    private val phonesHomeList: List<Phone>?
) : RecyclerView.Adapter<PhonesHomeAdapter.ViewHolder>() {
    private lateinit var binding: ItemPhoneHomeBinding

    class ViewHolder(private val binding: ItemPhoneHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(phone: Phone?) {
            binding.apply {
                Glide.with(image.context)
                    .load(phone?.image)
                    .into(image)

                name.text = phone?.name
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
        holder.bind(phonesHomeList?.get(position))
    }

    override fun getItemCount() = phonesHomeList!!.size
}