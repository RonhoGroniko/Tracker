package com.example.tracker.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tracker.R
import com.example.tracker.common.enums.GameMode
import com.example.tracker.common.extentions.parcelable
import com.example.tracker.data.mappers.toStatItems
import com.example.tracker.databinding.FragmentGameStatsBinding
import com.example.tracker.ui.adapters.GameModeAdapter
import com.example.tracker.ui.adapters.SeasonAdapter
import com.example.tracker.ui.adapters.StatsAdapter
import com.example.tracker.ui.models.PlayerInfoUiModel
import com.example.tracker.ui.models.PlayerSeasonGameModeStatsUiModel

private const val ARG_PLAYER_STATS = "playerStats"
private const val ARG_PLAYER_INFO = "playerInfo"


class GameStatsFragment : Fragment() {

    private lateinit var modeAdapter: GameModeAdapter
    private lateinit var seasonAdapter: SeasonAdapter

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
        arguments?.let {
            playerInfo = it.parcelable(ARG_PLAYER_INFO)
            playerStats = it.parcelable(ARG_PLAYER_STATS)
        }
        viewModel.initPlayer(playerInfo?.id ?: "")
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
        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel() {
        viewModel.selectedSeason.observe(viewLifecycleOwner) {
            Log.d(TAG, it)
            binding.seasonDropdown.setText(it, false)
        }
        viewModel.playerSeasonInfo.observe(viewLifecycleOwner) {
            playerStats = it
        }
        viewModel.currentStats.observe(viewLifecycleOwner) { statsList ->
            binding.recyclerStats.adapter = StatsAdapter(statsList)
        }
    }

    private fun setupRv() {
        with(binding) {
            recyclerStats.layoutManager = GridLayoutManager(requireContext(), 3)
            recyclerStats.adapter =
                StatsAdapter(playerStats?.gameModeStats?.soloFpp?.toStatItems() ?: emptyList())
            textViewName.text = playerInfo?.name
        }
    }

    private fun setupDropDowns() {
        modeAdapter = GameModeAdapter(requireContext(), R.layout.item_dropdown, GameMode.entries)
        seasonAdapter = SeasonAdapter(requireContext(), R.layout.item_dropdown, emptyList())

        binding.modeDropdown.setAdapter(modeAdapter)
        binding.seasonDropdown.setAdapter(seasonAdapter)

        viewModel.gameModeList.observe(viewLifecycleOwner) {
            modeAdapter.setItems(it)
            modeAdapter.filter.filter(null)
        }
        viewModel.gameMode.observe(viewLifecycleOwner) {
            binding.modeDropdown.setText(it.toString(), false)
            modeAdapter.filter.filter(null)
        }
        viewModel.seasons.observe(viewLifecycleOwner) { list ->
            Log.d(TAG, "Seasons from VM: $list")
            seasonAdapter.setItems(list.map { it.name })
            seasonAdapter.filter.filter(null)
        }
    }

    private fun setupDropDownSeasonItemClickListener() {
        binding.seasonDropdown.setOnItemClickListener { parent, view, position, id ->
            val seasonName = parent.getItemAtPosition(position) as String
            viewModel.setSelectedSeason(seasonName)
        }
    }

    private fun setupDropDownModeItemClickListener() {
        binding.modeDropdown.setOnItemClickListener { parent, view, position, id ->
            val gameMode = parent.getItemAtPosition(position) as GameMode
            viewModel.setGameMode(gameMode)
        }
    }

    companion object {

        private const val TAG = "GameStatsFragment"

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