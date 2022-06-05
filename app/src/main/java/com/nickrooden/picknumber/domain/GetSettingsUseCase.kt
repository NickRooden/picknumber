package com.nickrooden.picknumber.domain

class GetSettingsUseCase(private val repository: Repository){

    operator fun invoke(level: Level) : Settings{
        return  repository.getSettings(level)
    }
}