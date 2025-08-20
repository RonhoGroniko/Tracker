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
import com.example.tracker.ui.models.PlayerInfoUiModel
import com.example.tracker.ui.models.PlayerSeasonGameModeStatsUiModel

private const val ARG_PLAYER_STATS = "playerStats"
private const val ARG_PLAYER_INFO = "playerInfo"


class GameStatsFragment : Fragment() {

    private lateinit var modeAdapter: ArrayAdapter<GameMode>
    private lateinit var seasonAdapter: ArrayAdapter<String>

    private var playerInfo: PlayerInfoUiModel? = null
    private var playerStats: PlayerSeasonGameModeStatsUiModel? = null

    private val viewModel by lazy {
        ViewModelProvider(this)[GameStatsViewModel::class.java]
    }

    private var _binding: FragmentGameStatsBinding? = null
    private val binding: FragmentGameStatsBinding
        get() = _binding ?: throw RuntimeException("FragmentGameStatsBinding == null")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCurrentSeason()
        viewModel.getSeasons()
        arguments?.let {
            playerInfo = it.parcelable(ARG_PLAYER_INFO)
            playerStats = it.parcelable(ARG_PLAYER_STATS)
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
        setupDropDowns()
        setupDropDownModeItemClickListener()
        setupDropDownSeasonItemClickListener()
        viewModel.currentSeason.observe(viewLifecycleOwner) {
            binding.seasonDropdown.setText(it, false)
        }
        viewModel.playerSeasonInfo.observe(viewLifecycleOwner) {
            playerStats = it
            viewModel.gameMode.observe(viewLifecycleOwner) { gameMode -> updateRV(gameMode) }
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
                StatsAdapter(playerStats?.gameModeStats?.solo?.toStatItems() ?: emptyList())
            textViewName.text = playerInfo?.name
        }
    }

    private fun setupDropDowns() {
        modeAdapter = ArrayAdapter(requireContext(), R.layout.item_dropdown, mutableListOf())
        seasonAdapter = ArrayAdapter(requireContext(), R.layout.item_dropdown, mutableListOf())

        binding.modeDropdown.setAdapter(modeAdapter)
        binding.seasonDropdown.setAdapter(seasonAdapter)

        viewModel.gameModeList.observe(viewLifecycleOwner) {
            modeAdapter.clear()
            modeAdapter.addAll(it)
        }
        viewModel.gameMode.observe(viewLifecycleOwner) {
            binding.modeDropdown.setText(it.toString(), false)
        }
        viewModel.seasons.observe(viewLifecycleOwner) { list ->
            seasonAdapter.clear()
            seasonAdapter.addAll(list.map { it.name })
        }
    }

    private fun setupDropDownSeasonItemClickListener() {
        binding.seasonDropdown.setOnItemClickListener { parent, view, position, id ->
            val seasonName = parent.getItemAtPosition(position) as String
            viewModel.loadPlayerSeasonInfo(playerInfo?.id ?: "", seasonName)
        }
    }

    private fun setupDropDownModeItemClickListener() {
        binding.modeDropdown.setOnItemClickListener { parent, view, position, id ->
            val gameMode = parent.getItemAtPosition(position) as GameMode
            viewModel.setGameMode(gameMode)
            updateRV(gameMode)
        }
    }

    private fun updateRV(gameMode: GameMode) {
        val statsList = when (gameMode) {
            GameMode.DUO -> playerStats?.gameModeStats?.duo?.toStatItems() ?: emptyList()
            GameMode.DUO_FPP -> playerStats?.gameModeStats?.duoFpp?.toStatItems() ?: emptyList()
            GameMode.SOLO -> playerStats?.gameModeStats?.solo?.toStatItems() ?: emptyList()
            GameMode.SOLO_FPP -> playerStats?.gameModeStats?.soloFpp?.toStatItems() ?: emptyList()
            GameMode.SQUAD -> playerStats?.gameModeStats?.squad?.toStatItems() ?: emptyList()
            GameMode.SQUAD_FPP -> playerStats?.gameModeStats?.squadFpp?.toStatItems() ?: emptyList()
        }
        binding.recyclerStats.adapter = StatsAdapter(statsList)
    }

    companion object {

        @JvmStatic
        fun newInstance(
            playerInfo: PlayerInfoUiModel,
            playerSeasonGameModeStatsUiModel: PlayerSeasonGameModeStatsUiModel
        ) =
            GameStatsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PLAYER_INFO, playerInfo)
                    putParcelable(ARG_PLAYER_STATS, playerSeasonGameModeStatsUiModel)
                }
            }
    }
}