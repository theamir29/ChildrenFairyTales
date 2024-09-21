package com.example.erteklerqosqlarhmtaqmaqlar.view.matching

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.erteklerqosqlarhmtaqmaqlar.R
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.ItemGameSimilarBinding

class FindSimilarAdapter(
    private val first: Boolean,
    private val lifecycleOwner: LifecycleOwner,
    private val isSelectedLiveData: LiveData<Boolean>,
) :
    ListAdapter<saykesModel, FindSimilarAdapter.FindSimilarViewHolder>(object :
        DiffUtil.ItemCallback<saykesModel>() {

        override fun areItemsTheSame(oldItem: saykesModel, newItem: saykesModel) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: saykesModel, newItem: saykesModel) =
            oldItem.variant1 == newItem.variant1 && oldItem.variant2 == newItem.variant2
    }) {

    private var clickedItemIndex: Int? = null

    inner class FindSimilarViewHolder(private val binding: ItemGameSimilarBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var isSelected: Boolean = false

        init {
            isSelectedLiveData.observe(lifecycleOwner, Observer { isSelected ->
                this.isSelected = isSelected
                updatePlayPauseIcon()
            })
        }

        @SuppressLint("ResourceAsColor")
        fun bind(position: Int) {
            val data = getItem(position)
            binding.ivPicture1.setImageResource(if (first) data.variant1 else data.variant2)
            updateButtonStates(position)

            binding.root.setOnClickListener {
                onItemClickListener?.invoke(data)
                clickedItemIndex = adapterPosition
                notifyDataSetChanged()
            }
        }

        fun updatePlayPauseIcon() {
            if (isSelected) {
                binding.strokeAnswer1.setBackgroundResource(R.color.selected_color_answer)
            } else {
                binding.strokeAnswer1.setBackgroundResource(R.color.default_color_answer)
            }
        }

        fun updateButtonStates(position: Int) {
            if (position == clickedItemIndex) {
                updatePlayPauseIcon()
            } else {
                resetButtonState()
            }
        }

        private fun resetButtonState() {
            binding.strokeAnswer1.setBackgroundResource(R.color.default_color_answer)
        }
    }

    fun removeItem(item: saykesModel) {
        val list = currentList.toMutableList()
        list.remove(item)
        submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FindSimilarViewHolder(
        ItemGameSimilarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: FindSimilarViewHolder, position: Int) {
        holder.bind(position)
    }

    private var onItemClickListener: ((saykesModel) -> Unit)? = null
    fun setOnItemClickListener(block: ((saykesModel) -> Unit)) {
        onItemClickListener = block
    }
}
