package com.example.tracker.ui.adapters

import android.content.Context
import android.widget.ArrayAdapter

class SeasonAdapter(
    context: Context,
    resource: Int,
    items: List<String>
) : ArrayAdapter<String>(context, resource, ArrayList(items)) {

    fun setItems(newItems: List<String>) {
        clear()
        addAll(newItems)
        notifyDataSetChanged()
    }
}