package com.example.erteklerqosqlarhmtaqmaqlar.view.taqmaq

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.erteklerqosqlarhmtaqmaqlar.R
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Taqmaq
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.ItemTaqmaqBinding
import com.example.erteklerqosqlarhmtaqmaqlar.utils.setImage

class TaqmaqAdapter : ListAdapter<Taqmaq, TaqmaqAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<Taqmaq>() {
        override fun areItemsTheSame(oldItem: Taqmaq, newItem: Taqmaq) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Taqmaq, newItem: Taqmaq) = oldItem.id == newItem.id
    }
) {

    inner class ViewHolder(private val binding: ItemTaqmaqBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val taqmaq = getItem(position)
            try {
                binding.ivTaqmaq.setImage(taqmaq.image)
            } catch (e: Exception) {
                e.printStackTrace()
                binding.ivTaqmaq.setImageResource(R.drawable.ertek_example)
            }
//            binding.tvTaqmaqName.text = taqmaq.name
//            binding.tvTaqmaqAvtor.text = "Xalq taqmaqları"
            if (taqmaq.is_saved == 1) {
                binding.ivLike.setImage("ic_like_red")
            } else {
                binding.ivLike.setImage("ic_like")
            }

            binding.ivLike.setOnClickListener {
                val isSaved = (taqmaq.is_saved + 1) % 2
                onLikeClickListener?.invoke(taqmaq.id, isSaved)
            }

            binding.root.setOnClickListener {
                onItemClickListener?.invoke(taqmaq.id)
            }
        }
    }

    private var onLikeClickListener: ((Int, Int) -> Unit)? = null
    fun setOnLikeClickListener(block: ((Int, Int) -> Unit)) {
        onLikeClickListener = block
    }

    private var onItemClickListener: ((Int) -> Unit)? = null
    fun setOnItemClickListener(block: ((Int) -> Unit)) {
        onItemClickListener = block
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemTaqmaqBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(position)
}