package com.ajailani.spesipon.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajailani.spesipon.data.model.phone.Phone
import com.ajailani.spesipon.databinding.ItemPhoneHomeBinding

class PhonesHomeAdapter(
    private val phonesHomeList: List<Phone>
) : RecyclerView.Adapter<PhonesHomeAdapter.ViewHolder>() {
    private lateinit var binding: ItemPhoneHomeBinding

    class ViewHolder(private val binding: ItemPhoneHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemPhoneHomeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount() = phonesHomeList.size
}