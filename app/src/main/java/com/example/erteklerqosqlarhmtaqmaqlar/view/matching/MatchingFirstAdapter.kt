package com.example.erteklerqosqlarhmtaqmaqlar.view.matching

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.erteklerqosqlarhmtaqmaqlar.R
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.ItemGameSimilarBinding

class MatchingFirstAdapter : ListAdapter<saykesModel, MatchingFirstAdapter.ViewHolder>(object :
    DiffUtil.ItemCallback<saykesModel>() {

    override fun areItemsTheSame(oldItem: saykesModel, newItem: saykesModel) =
        oldItem == newItem // Сравнение по ссылке, так как идентификатор отсутствует

    override fun areContentsTheSame(oldItem: saykesModel, newItem: saykesModel) =
        oldItem.variant1 == newItem.variant1
}) {

    inner class ViewHolder(private val binding: ItemGameSimilarBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val data = getItem(position)
            binding.ivPicture1.setImageResource(data.variant1)

            var selected = false
            binding.root.setOnClickListener {
                selected = selected.not()
                if (selected) {
                    binding.strokeAnswer1.setBackgroundResource(R.color.selected_color_answer)
                } else binding.strokeAnswer1.setBackgroundResource(R.color.default_color_answer)
                onItemClickListener?.invoke(data, selected)
            }
        }
    }

    private var onItemClickListener: ((saykesModel, Boolean) -> (Unit))? = null
    fun setOnItemClickListener(block: ((saykesModel, Boolean) -> Unit)) {
        onItemClickListener = block
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemGameSimilarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(position)
}
