package com.nickrooden.picknumber.presentation

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nickrooden.picknumber.R
import com.nickrooden.picknumber.data.RepositoryImpl
import com.nickrooden.picknumber.domain.*

class GameViewModel(
    private val level: Level,
    private val application: Application
) : ViewModel(){

    private val repository = RepositoryImpl

    private val getSettingsUseCase = GetSettingsUseCase(repository)
    private val getQuestionUseCase = GetQuestionUseCase(repository)

    private lateinit var  settings : Settings

    private var timer: CountDownTimer? = null

    private var _timerStr = MutableLiveData<String>()
    val timerStr : LiveData<String> get() = _timerStr

    private var _question = MutableLiveData<Question>()
    val question : LiveData<Question> get() = _question

    private var _percentOfRightAnswer = MutableLiveData<Int>()
    val percentOfRightAnswer : LiveData<Int> get() = _percentOfRightAnswer

    private var _progressAnswers = MutableLiveData<String>()
    val progressAnswers : LiveData<String> get() = _progressAnswers

    private var _enoughCountOfRightAnswers = MutableLiveData<Boolean>()
    val enoughCountOfRightAnswers: LiveData<Boolean> get() = _enoughCountOfRightAnswers

    private var _enoughPercentOfRightAnswers = MutableLiveData<Boolean>()
    val enoughPercentOfRightAnswers: LiveData<Boolean>  get() = _enoughPercentOfRightAnswers

    private var _minPercentOfRightAnswer = MutableLiveData<Int>()
    val minPercentOfRightAnswer : LiveData<Int> get() = _minPercentOfRightAnswer

    private var _gameResultGm = MutableLiveData<ResultGm>()
    val gameResultGm : LiveData<ResultGm> get() = _gameResultGm


    private var countOfRightAnswers = 0
    private var countOfQuestions = 0




    init {
        setSettings()

        startTimer()

        getQuestion()

        updateProgress()
    }

    fun chooseAnswer(number: Int){
        checkAnswer(number)
        updateProgress()
        getQuestion()
    }

    private fun updateProgress(){
        val percent = calculatePercentOfRightAnswers()
        _percentOfRightAnswer.value = percent
        _progressAnswers.value = String.format(
            application.resources.getString(R.string.progress_answers),
            countOfRightAnswers,
            settings.minQuestions
        )
        _enoughCountOfRightAnswers.value = countOfRightAnswers >= settings.minQuestions
        _enoughPercentOfRightAnswers.value = percent >= settings.minPercent
    }

    private fun calculatePercentOfRightAnswers(): Int{
        if (countOfQuestions == 0){
            return 0
        }
        return ((countOfRightAnswers/countOfQuestions.toDouble())*100).toInt()
    }

    private fun checkAnswer(number: Int){
        val rightAnswer = question.value?.rightAnswer
        if (number == rightAnswer){
            countOfRightAnswers++
        }
        countOfQuestions++
    }

    private fun getQuestion(){
        _question.value = getQuestionUseCase(settings.maxSum)

    }

    private fun setSettings() {
         this.settings = getSettingsUseCase(level)
        _minPercentOfRightAnswer.value = settings.minPercent
    }

    private fun startTimer(){
        timer = object : CountDownTimer(
            settings.timer * MILSEC_IN_SEC, MILSEC_IN_SEC){
            override fun onTick(p0: Long) {
                val sec = p0 / MILSEC_IN_SEC
                val minutes = sec / 60
                val lastSec = sec - minutes * 60
                _timerStr.value = String.format("%02d:%02d", minutes, lastSec)
            }

            override fun onFinish() {
                finishGame()
            }

        }
        timer?.start()
    }

    fun finishGame(){

        _gameResultGm.value = ResultGm(
            enoughCountOfRightAnswers.value == true
                    && enoughPercentOfRightAnswers.value == true,
            countOfRightAnswers,
            countOfQuestions,
            settings
        )

    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

    companion object{
        private const val MILSEC_IN_SEC = 1000L
    }



}