package com.example.erteklerqosqlarhmtaqmaqlar.view.ertek

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.erteklerqosqlarhmtaqmaqlar.R
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.feedback.GetFeedbackData
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.feedback.GetFeedbackItem
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.feedback.GetFeedbackResponseData
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Ertek
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.ItemErtekBinding
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.ItemNewsMessageBinding
import com.example.erteklerqosqlarhmtaqmaqlar.utils.setImage

class GetFeedbackAdapter : ListAdapter<GetFeedbackItem, GetFeedbackAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<GetFeedbackItem>() {
        override fun areItemsTheSame(oldItem: GetFeedbackItem, newItem: GetFeedbackItem) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: GetFeedbackItem, newItem: GetFeedbackItem) =
            oldItem.id == newItem.id
    }
)
    {
    inner class ViewHolder(private val binding: ItemNewsMessageBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val newsMessage = getItem(position)
            binding.tvTitle.text = newsMessage.feedback

            binding.ratingBar.rating = newsMessage.rating.toFloat()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemNewsMessageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(position)
}