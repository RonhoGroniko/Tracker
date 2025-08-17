package com.example.tracker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tracker.R
import com.example.tracker.common.enums.GameMode
import com.example.tracker.common.extentions.parcelable
import com.example.tracker.data.mappers.toStatItems
import com.example.tracker.databinding.FragmentGameStatsBinding
import com.example.tracker.ui.adapters.StatsAdapter
import com.example.tracker.ui.models.PlayerSeasonGameModeStatsUiModel

private const val ARG_PLAYER_STATS = "playerStats"
private const val ARG_PLAYER_NAME = "playerName"


class GameStatsFragment : Fragment() {

    private val gameModes = GameMode.entries
    private var gameMode = GameMode.SOLO
    private var playerName: String? = null
    private var playerInfo: PlayerSeasonGameModeStatsUiModel? = null

    private val viewModel by lazy {
        ViewModelProvider(this)[GameStatsViewModel::class.java]
    }

    private var _binding: FragmentGameStatsBinding? = null
    private val binding: FragmentGameStatsBinding
        get() = _binding ?: throw RuntimeException("FragmentGameStatsBinding == null")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            playerName = it.getString(ARG_PLAYER_NAME)
            playerInfo = it.parcelable(ARG_PLAYER_STATS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameStatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        setupDropDown()
        setupOnDropDownItemClickListener()
        viewModel.seasons.observe(viewLifecycleOwner) {list ->
            val adapter = ArrayAdapter(requireContext(), R.layout.item_dropdown, list.map { it.name })
            binding.seasonDropdown.setAdapter(adapter)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRv() {
        with(binding) {
            recyclerStats.layoutManager = GridLayoutManager(requireContext(), 3)
            recyclerStats.adapter =
                StatsAdapter(playerInfo?.gameModeStats?.solo?.toStatItems() ?: emptyList())
            textViewName.text = playerName
        }
    }

    private fun setupDropDown() {
        val adapter = ArrayAdapter(requireContext(), R.layout.item_dropdown, gameModes)
        binding.modeDropdown.setAdapter(adapter)
    }

    private fun setupOnDropDownItemClickListener() {
        binding.modeDropdown.setOnItemClickListener { parent, view, position, id ->
            gameMode = parent.getItemAtPosition(position) as GameMode
            val statsList = when (gameMode) {
                GameMode.DUO -> playerInfo?.gameModeStats?.duo?.toStatItems() ?: emptyList()
                GameMode.DUO_FPP -> playerInfo?.gameModeStats?.duoFpp?.toStatItems() ?: emptyList()
                GameMode.SOLO -> playerInfo?.gameModeStats?.solo?.toStatItems() ?: emptyList()
                GameMode.SOLO_FPP -> playerInfo?.gameModeStats?.soloFpp?.toStatItems() ?: emptyList()
                GameMode.SQUAD -> playerInfo?.gameModeStats?.squad?.toStatItems() ?: emptyList()
                GameMode.SQUAD_FPP -> playerInfo?.gameModeStats?.squadFpp?.toStatItems() ?: emptyList()
            }
            binding.recyclerStats.adapter = StatsAdapter(statsList)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(
            playerName: String,
            playerSeasonGameModeStatsUiModel: PlayerSeasonGameModeStatsUiModel
        ) =
            GameStatsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PLAYER_NAME, playerName)
                    putParcelable(ARG_PLAYER_STATS, playerSeasonGameModeStatsUiModel)
                }
            }
    }
}