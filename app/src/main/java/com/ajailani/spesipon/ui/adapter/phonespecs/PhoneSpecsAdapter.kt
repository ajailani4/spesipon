package com.ajailani.spesipon.ui.adapter.phonespecs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ajailani.spesipon.data.model.PhoneSpecs
import com.ajailani.spesipon.databinding.ItemSpecsBinding

class PhoneSpecsAdapter(
    private val phoneSpecsList: List<PhoneSpecs>
) : RecyclerView.Adapter<PhoneSpecsAdapter.ViewHolder>() {
    private lateinit var binding: ItemSpecsBinding
    private lateinit var phoneSubSpecsAdapter: PhoneSubSpecsAdapter
    private val rvViewPool = RecyclerView.RecycledViewPool()

    class ViewHolder(private val binding: ItemSpecsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(phoneSpecs: PhoneSpecs) {
            binding.apply {
                title.text = phoneSpecs.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemSpecsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(phoneSpecsList[position])

        // Setup phoneSubSpecsAdapter and phoneSpecsRv
        phoneSubSpecsAdapter = PhoneSubSpecsAdapter(phoneSpecsList[position].specs)
        binding.phoneSubSpecsRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = phoneSubSpecsAdapter
            setRecycledViewPool(rvViewPool)
        }
    }

    override fun getItemCount() = phoneSpecsList.size
}