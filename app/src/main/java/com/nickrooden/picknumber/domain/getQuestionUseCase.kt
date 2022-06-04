package com.nickrooden.picknumber.domain

class getQuestionUseCase(private val repository: Repository) {
    operator fun invoke(maxSum: Int) : Question{
       return repository.getQuestion(maxSum)
    }
}