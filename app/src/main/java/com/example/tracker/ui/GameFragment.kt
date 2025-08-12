package com.example.tracker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tracker.R
import com.example.tracker.common.extentions.parcelable
import com.example.tracker.databinding.FragmentGameBinding
import com.example.tracker.ui.models.GameName

private const val ARG_GAME_NAME = "GAME_NAME"

class GameFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this)[GameFragmentViewModel::class.java]
    }
    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")
    private var gameName: GameName? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            gameName = it.parcelable(ARG_GAME_NAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        setRightGameImage()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setupTextChangeListeners()
        viewModel.inputText.observe(viewLifecycleOwner) {
            if (binding.editTextName.text.toString() == it) {
                return@observe
            }
            binding.editTextName.setText(it)
        }
        binding.textInputLayoutName.setEndIconOnClickListener {
            viewModel.loadCurrentSeasonPLayerInfo()
        }
        binding.editTextName.doAfterTextChanged { text ->
            viewModel.changeString(text.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel() {
        viewModel.errorLD.observe(viewLifecycleOwner) {isError ->
            val message = if (isError) {
                binding.textInputLayoutName.context.getString(R.string.error_input_name)
            } else {
                null
            }
            binding.textInputLayoutName.error = message
        }
    }

    private fun setupTextChangeListeners() {
        binding.editTextName.doOnTextChanged { _, _, _, _ ->
            viewModel.resetInputNameError()
        }
    }

    private fun showTODOToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    private fun setRightGameImage() {
        when (gameName) {
            GameName.PUBG -> binding.imageGame.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.pubg
                )
            )
            GameName.VALORANT -> showTODOToast("TODO Set drawable Valorant")
            GameName.MARVEL_RIVALS -> showTODOToast("TODO Set drawable MARVEL_RIVALS")
            null -> TODO()
        }
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