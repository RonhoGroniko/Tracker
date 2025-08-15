package com.example.tracker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.tracker.R
import com.example.tracker.common.extentions.parcelable
import com.example.tracker.ui.models.PlayerSeasonGameModeStatsUiModel

private const val ARG_PLAYER_STATS = "playerStats"


class GameStatsFragment : Fragment() {

    private var playerInfo: PlayerSeasonGameModeStatsUiModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            playerInfo = it.parcelable(ARG_PLAYER_STATS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.textViewTest).apply {
            text = playerInfo.toString()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(playerSeasonGameModeStatsUiModel: PlayerSeasonGameModeStatsUiModel) =
            GameStatsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PLAYER_STATS, playerSeasonGameModeStatsUiModel)
                }
            }
    }
}