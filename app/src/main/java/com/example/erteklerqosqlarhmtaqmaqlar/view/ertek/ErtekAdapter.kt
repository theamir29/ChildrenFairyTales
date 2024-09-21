package com.example.erteklerqosqlarhmtaqmaqlar.view.ertek

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.erteklerqosqlarhmtaqmaqlar.R
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Ertek
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.ItemErtekBinding
import com.example.erteklerqosqlarhmtaqmaqlar.utils.setImage

class ErtekAdapter : ListAdapter<Ertek, ErtekAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<Ertek>() {
        override fun areItemsTheSame(oldItem: Ertek, newItem: Ertek) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Ertek, newItem: Ertek) = oldItem.id == newItem.id
    }
) {

    inner class ViewHolder(private val binding: ItemErtekBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val ertek = getItem(position)
            try {
                binding.ivErtek.setImage(ertek.image)
            } catch (e: Exception) {
                e.printStackTrace()
                binding.ivErtek.setImageResource(R.drawable.ertek_example)
            }
            if (ertek.is_saved == 1) {
                binding.ivLike.setImage("ic_like_red")
            } else {
                binding.ivLike.setImage("ic_like")
            }

            binding.ivLike.setOnClickListener {
                val isSaved = (ertek.is_saved + 1) % 2
                onLikeClickListener?.invoke(ertek.id, isSaved)
            }

            binding.root.setOnClickListener {
                onItemClickListener?.invoke(ertek)
            }
        }
    }

    private var onLikeClickListener: ((Int, Int) -> Unit)? = null
    fun setOnLikeClickListener(block: ((Int, Int) -> Unit)) {
        onLikeClickListener = block
    }

    private var onItemClickListener: ((Ertek) -> Unit)? = null
    fun setOnItemClickListener(block: ((Ertek) -> Unit)) {
        onItemClickListener = block
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemErtekBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(position)
}