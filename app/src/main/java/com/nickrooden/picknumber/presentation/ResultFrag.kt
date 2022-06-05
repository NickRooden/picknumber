package com.nickrooden.picknumber.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nickrooden.picknumber.R
import com.nickrooden.picknumber.databinding.ResultFragBinding

class ResultFrag : Fragment() {

    private var _binding : ResultFragBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<ResultFragArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ResultFragBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.resultgm = args.resultgm

        binding.buttonAgain.setOnClickListener {
            retryGame()
        }

    }

    fun retryGame(){
        findNavController().popBackStack()

    }

}