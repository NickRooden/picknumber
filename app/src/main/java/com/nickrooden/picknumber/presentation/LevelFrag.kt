package com.nickrooden.picknumber.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nickrooden.picknumber.databinding.LevelFragBinding
import com.nickrooden.picknumber.domain.Level

class LevelFrag : Fragment() {

    private var _binding : LevelFragBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LevelFragBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.testButton.setOnClickListener {
            launchGameFragment(Level.TEST)
        }
        binding.easyButton.setOnClickListener {
            launchGameFragment(Level.EASY)
        }
        binding.normalButton.setOnClickListener {
            launchGameFragment(Level.NORMAL)
        }
        binding.hardButton.setOnClickListener {
            launchGameFragment(Level.HARD)
        }
    }

    private fun launchGameFragment(level: Level) {

        findNavController().navigate(
            LevelFragDirections.actionLevelFragToGameFrag(level)
        )


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}