package com.nickrooden.picknumber.data

import com.nickrooden.picknumber.domain.Level
import com.nickrooden.picknumber.domain.Question
import com.nickrooden.picknumber.domain.Repository
import com.nickrooden.picknumber.domain.Settings
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

object RepositoryImpl : Repository {

    const val MIN_OPT_ANSWERS = 6
    const val MIN_SEENMBR = 2
    const val MIN_SUM = 4

    override fun getSettings(level: Level): Settings {
       return when (level){
            Level.TEST -> Settings(10,2,50,6)
            Level.EASY -> Settings(10,5,50,10)
            Level.NORMAL -> Settings(20,10,60,11)
            Level.HARD -> Settings(30,15,80,15)
        }
    }

    override fun getQuestion(maxSum: Int): Question {

        val sum = Random.nextInt(MIN_SUM, maxSum + 1)
        val seeNumber = Random.nextInt(MIN_SEENMBR, sum - 2)
        val rightAnswer = sum - seeNumber
        val optAnswers = hashSetOf<Int>()
        optAnswers.add(rightAnswer)

        val from = max(2,rightAnswer - MIN_OPT_ANSWERS)
        val to = min(maxSum, rightAnswer + MIN_OPT_ANSWERS)
        while (optAnswers.size < MIN_OPT_ANSWERS){

            val opt = Random.nextInt(from, to)
            optAnswers.add(opt)

        }
        return Question(sum, seeNumber, optAnswers.toList())
    }


}