package com.example.assignment2_vaishali

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2_vaishali.databinding.ItemCountryBinding

class CountriesAdapter(
    private val items: MutableList<String>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<CountriesAdapter.VH>() {

    interface OnItemClickListener {
        fun onItemClick(country: String)
        fun onItemLongClick(position: Int)
    }

    inner class VH(val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val pos = bindingAdapterPosition
                if (pos != RecyclerView.NO_POSITION) listener.onItemClick(items[pos])
            }
            binding.root.setOnLongClickListener {
                val pos = bindingAdapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    listener.onItemLongClick(pos)
                }
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val b = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(b)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.tvCountry.text = items[position]
        holder.binding.ivFlag.contentDescription = "${items[position]} flag"
    }

    override fun getItemCount() = items.size
}