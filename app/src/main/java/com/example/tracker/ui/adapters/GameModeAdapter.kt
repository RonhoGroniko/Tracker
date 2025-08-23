package com.example.tracker.ui.adapters

import android.content.Context
import android.widget.ArrayAdapter
import com.example.tracker.common.enums.GameMode

class GameModeAdapter(
    context: Context,
    resource: Int,
    private var items: List<GameMode>
) : ArrayAdapter<GameMode>(context, resource, items) {

    fun setItems(newItems: List<GameMode>) {
        items = newItems
        notifyDataSetChanged()
    }
}