package com.nickrooden.picknumber.data

import com.nickrooden.picknumber.domain.Level
import com.nickrooden.picknumber.domain.Question
import com.nickrooden.picknumber.domain.Repository
import com.nickrooden.picknumber.domain.Settings

object RepositoryImpl : Repository {
    override fun getSettings(level: Level): Settings {
        TODO("Not yet implemented")
    }

    override fun getQuestion(maxSum: Int): Question {
        TODO("Not yet implemented")
    }
}