package com.nickrooden.picknumber.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Result(
    val winner: Boolean,
    val rightAnswers: Int,
    val allAnswers: Int,
    val settings: Settings
) : Parcelable
