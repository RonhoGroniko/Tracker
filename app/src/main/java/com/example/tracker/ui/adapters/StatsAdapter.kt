package com.example.tracker.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.databinding.ItemStatsCardBinding
import com.example.tracker.ui.models.StatItemUiModel

class StatsAdapter(
    private val items: List<StatItemUiModel>
) : RecyclerView.Adapter<StatsAdapter.StatViewHolder>() {

    inner class StatViewHolder(val binding: ItemStatsCardBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: StatItemUiModel) {
            binding.iconStat.setImageResource(item.iconRes)
            binding.titleStat.text = item.title
            binding.valueStat.text = item.value
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatViewHolder {
        val binding = ItemStatsCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StatViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}