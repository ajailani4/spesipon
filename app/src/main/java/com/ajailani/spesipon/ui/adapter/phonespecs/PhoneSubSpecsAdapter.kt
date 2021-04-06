package com.ajailani.spesipon.ui.adapter.phonespecs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajailani.spesipon.R
import com.ajailani.spesipon.data.model.PhoneSubSpecs
import com.ajailani.spesipon.databinding.ItemSubSpecsBinding

class PhoneSubSpecsAdapter(
    private val subSpecsList: List<PhoneSubSpecs>
) : RecyclerView.Adapter<PhoneSubSpecsAdapter.ViewHolder>() {
    private lateinit var binding: ItemSubSpecsBinding

    class ViewHolder(private val binding: ItemSubSpecsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(phoneSubSpecs: PhoneSubSpecs) {
            binding.apply {
                subTitle.text = phoneSubSpecs.subTitle

                phoneSubSpecs.subSpecs.forEach {
                    if (phoneSubSpecs.subSpecs.size > 1) {
                        subSpecs.text = root.context.getString(R.string.subspecs_more_than_one, it)
                    } else {
                        subSpecs.text = it
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemSubSpecsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(subSpecsList[position])
    }

    override fun getItemCount() = subSpecsList.size
}