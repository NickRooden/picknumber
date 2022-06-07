package com.nickrooden.picknumber.presentation

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nickrooden.picknumber.databinding.GameFragBinding
import com.nickrooden.picknumber.domain.ResultGm

class GameFrag : Fragment() {

    private val args by navArgs<GameFragArgs>()

    private val viewModelFactory by lazy {
        GameViewModelFactory(args.level, requireActivity().application)
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[GameViewModel::class.java]
    }

    private val optins by lazy {
        mutableListOf<TextView>().apply {
            add(binding.opt1)
            add(binding.opt2)
            add(binding.opt3)
            add(binding.opt4)
            add(binding.opt5)
            add(binding.opt6)
        }
    }

    private var _binding : GameFragBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = GameFragBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        observeViewModel()
 //       setClickListenerOnOptions()
    }
//    private fun setClickListenerOnOptions(){
//        for (opt in optins){
//            opt.setOnClickListener {
//                viewModel.chooseAnswer(opt.text.toString().toInt())
//            }
//        }
//    }
    private fun observeViewModel() {
//        viewModel.timerStr.observe(viewLifecycleOwner) {
//            binding.timer.text = it.toString()
//        }

//        viewModel.question.observe(viewLifecycleOwner) {
//            binding.sum.text = it.sum.toString()
//            binding.visibleNbr.text = it.seeNumber.toString()
//            for (i in 0 until optins.size) {
//                optins[i].text = it.optNumbers[i].toString()
//            }
//        }

//        viewModel.percentOfRightAnswer.observe(viewLifecycleOwner) {
//            binding.progressBar.setProgress(it, true)
//        }
//        viewModel.enoughCountOfRightAnswers.observe(viewLifecycleOwner) {
//            binding.progressText.setTextColor(getColorByState(it))
//        }
//        viewModel.enoughPercentOfRightAnswers.observe(viewLifecycleOwner) {
//            val color = getColorByState(it)
//            binding.progressBar.progressTintList = ColorStateList.valueOf(color)
//        }
//        viewModel.minPercentOfRightAnswer.observe(viewLifecycleOwner) {
//            binding.progressBar.secondaryProgress = it
//        }
        viewModel.gameResultGm.observe(viewLifecycleOwner) {
            launchResultFragment(it)
        }
//        viewModel.progressAnswers.observe(viewLifecycleOwner){
//            binding.progressText.text = it
//        }
    }

    private fun launchResultFragment(result: ResultGm) {

        findNavController().navigate(
            GameFragDirections.actionGameFragToResultFrag(result)
        )

    }

//    private fun getColorByState(state: Boolean): Int{
//        val colorToBar = if (state){
//            android.R.color.holo_green_light
//        }else{
//            android.R.color.holo_red_light
//        }
//        return ContextCompat.getColor(requireContext(), colorToBar)
//    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}