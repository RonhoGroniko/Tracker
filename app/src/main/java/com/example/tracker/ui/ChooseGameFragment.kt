package com.example.tracker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tracker.R
import com.example.tracker.databinding.FragmentChooseGameBinding
import com.example.tracker.ui.models.GameName


class ChooseGameFragment : Fragment() {

    private var _binding: FragmentChooseGameBinding? = null
    private val binding: FragmentChooseGameBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseGameBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChooseGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cardPubg.setOnClickListener { startGameFragment(GameName.PUBG) }
        binding.cardValorant.setOnClickListener { startGameFragment(GameName.VALORANT) }
        binding.cardMarvelRivals.setOnClickListener { startGameFragment(GameName.MARVEL_RIVALS) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun startGameFragment(gameName: GameName) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFragment.newInstance(gameName))
            .addToBackStack(null)
            .commit()
    }

    companion object {

        @JvmStatic
        fun newInstance() = ChooseGameFragment()
    }
}