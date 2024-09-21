package com.example.erteklerqosqlarhmtaqmaqlar.view.qosiq

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.erteklerqosqlarhmtaqmaqlar.R
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Qosiq
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.ItemQosiqBinding
import com.example.erteklerqosqlarhmtaqmaqlar.utils.setImage

class QosiqAdapter : ListAdapter<Qosiq, QosiqAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<Qosiq>() {
        override fun areItemsTheSame(oldItem: Qosiq, newItem: Qosiq) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Qosiq, newItem: Qosiq) = oldItem.id == newItem.id
    }
) {

    inner class ViewHolder(private val binding: ItemQosiqBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val qosiq = getItem(position)
            try {
                binding.ivQosiq.setImage(qosiq.image)
            } catch (e: Exception) {
                e.printStackTrace()
                binding.ivQosiq.setImageResource(R.drawable.dau_menen_bala_bala)
            }
//            binding.tvQosiqName.text = qosiq.name
//            binding.tvQosiqAvtor.text = "Xalq qosıqları"
            if (qosiq.is_saved == 1) {
                binding.ivLike.setImage("ic_like_red")
            } else {
                binding.ivLike.setImage("ic_like")
            }

            binding.ivLike.setOnClickListener {
                val isSaved = (qosiq.is_saved + 1) % 2
                onLikeClickListener?.invoke(qosiq.id, isSaved)
            }

            binding.root.setOnClickListener {
                onItemClick.invoke(qosiq.id, absoluteAdapterPosition)
            }
        }
    }

    private var onLikeClickListener: ((Int, Int) -> Unit)? = null
    fun setOnLikeClickListener(block: ((Int, Int) -> Unit)) {
        onLikeClickListener = block
    }

    private var onItemClick: (id: Int, position: Int) -> Unit = { _, _ -> }
    fun setOnItemClickListener(onItemClick: (id: Int, position: Int) -> Unit) {
        this.onItemClick = onItemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemQosiqBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(position)
}