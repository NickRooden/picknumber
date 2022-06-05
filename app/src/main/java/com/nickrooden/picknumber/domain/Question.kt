package com.nickrooden.picknumber.domain


data class Question (
    val sum : Int,
    val seeNumber: Int,
    val optNumbers : List<Int>
        ){
    val rightAnswer get() = sum - seeNumber
}