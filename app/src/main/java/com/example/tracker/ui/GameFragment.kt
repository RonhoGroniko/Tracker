package com.example.tracker.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tracker.R
import com.example.tracker.ui.models.GameName

private const val ARG_GAME_NAME = "GAME_NAME"

class GameFragment : Fragment() {
    private var gameName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            gameName = it.getString(ARG_GAME_NAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(gameName: GameName) =
            GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_GAME_NAME, gameName)
                }
            }
    }
}