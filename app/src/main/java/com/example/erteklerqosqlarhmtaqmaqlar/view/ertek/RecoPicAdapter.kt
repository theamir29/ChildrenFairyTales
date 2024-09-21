package com.example.erteklerqosqlarhmtaqmaqlar.view.ertek

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.RecoPicData
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.ItemRecoPicBinding

class RecoPicAdapter : ListAdapter<RecoPicData, RecoPicAdapter.ViewHolder>(object :
    DiffUtil.ItemCallback<RecoPicData>() {
    override fun areItemsTheSame(oldItem: RecoPicData, newItem: RecoPicData) = oldItem == newItem

    override fun areContentsTheSame(oldItem: RecoPicData, newItem: RecoPicData) =
        oldItem.id == newItem.id
}) {

    inner class ViewHolder(private val binding: ItemRecoPicBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val data = getItem(position)
            binding.ivImage.setImageResource(data.pic)

            binding.root.setOnClickListener {
                onItemClickListener?.invoke(data)
            }

        }
    }

    private var onItemClickListener: ((RecoPicData) -> Unit)? = null
    fun setOnItemClickListener(block: ((RecoPicData) -> Unit)) {
        onItemClickListener = block
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemRecoPicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(position)
}