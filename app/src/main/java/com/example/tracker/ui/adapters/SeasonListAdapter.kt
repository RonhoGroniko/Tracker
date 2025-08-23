package com.example.tracker.ui.adapters

import android.content.Context
import android.widget.ArrayAdapter

class SeasonAdapter(
    context: Context,
    resource: Int,
    private var items: List<String>
) : ArrayAdapter<String>(context, resource, items) {

    fun setItems(newItems: List<String>) {
        items = newItems
        notifyDataSetChanged()
    }
}