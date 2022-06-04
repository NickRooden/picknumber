package com.nickrooden.picknumber.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Settings(
    val maxSum: Int,
    val minQuestions: Int,
    val minPercent: Int,
    val timer: Int
) : Parcelable
