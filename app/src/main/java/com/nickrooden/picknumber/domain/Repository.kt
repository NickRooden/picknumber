package com.nickrooden.picknumber.domain

interface Repository {

    fun getSettings(level: Level) : Settings

    fun getQuestion(maxSum: Int) : Question
}