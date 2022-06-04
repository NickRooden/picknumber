package com.nickrooden.picknumber.domain

class getSettingsUseCase(private val repository: Repository){

    operator fun invoke(level: Level) : Settings{
        return  repository.getSettings(level)
    }
}