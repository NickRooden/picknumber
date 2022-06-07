package com.nickrooden.picknumber.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.nickrooden.picknumber.R
import com.nickrooden.picknumber.domain.ResultGm

@BindingAdapter ("resultImageSource")
fun bindResultImageSource(imageView: ImageView, winner: Boolean){
    imageView.setImageResource(getFinishImage(winner))
}
private fun getFinishImage(winner: Boolean) :  Int{
    return if (winner){
        R.drawable.giftbox
    }else{
        R.drawable.closed
    }

}
@BindingAdapter ("winnerText")
fun bindWinnerText(textView: TextView, winner: Boolean){
    textView.text = if (winner){
        textView.context.getString(R.string.winner)
    }else{
        textView.context.getString(R.string.loser)
    }
}
@BindingAdapter ("answersToWinText")
fun bindAnswersToWinText(textView: TextView, minQuestions: Int){
    textView.text = String.format(
        textView.context.getString(R.string.answers_to_win),
        minQuestions
    )
}
@BindingAdapter("rightAnswersText")
fun bindRightAnswersText(textView: TextView, rightAnswers: Int){
    textView.text = String.format(
        textView.context.getString(R.string.right_answers),
        rightAnswers
    )
}
@BindingAdapter("minPercentText")
fun bindMinPercentText(textView: TextView, minPercent: Int){
    textView.text = String.format(
        textView.context.getString(R.string.percent_to_win),
        minPercent
    )
}
@BindingAdapter("rightPercentText")
fun bindRightPercentText(textView: TextView, resultgm: ResultGm){
    textView.text = String.format(
        textView.context.getString(R.string.right_percent),
        rightPercent(resultgm)
    )
}
private fun rightPercent(resultgm: ResultGm) = with(resultgm){
    if (rightAnswers == 0){
        "0"
    }else{
        ((rightAnswers / allAnswers.toDouble()) * 100).toString()
    }

}
// GameFragment
@BindingAdapter("numberToText")
fun bindNumberToText(textView: TextView, number: Int){
    textView.text = number.toString()
}

@BindingAdapter("setColorText")
fun bindSetColorText(textView: TextView, greenColor: Boolean){
    textView.setTextColor(getColorByState(textView.context, greenColor))
}
@BindingAdapter("setColorBar")
fun bindSetColorBar(bar: ProgressBar, greenColor: Boolean){
    val color = getColorByState(bar.context, greenColor)
          bar.progressTintList = ColorStateList.valueOf(color)
}
private fun getColorByState(context: Context, state: Boolean): Int{
    val colorToBar = if (state){
        android.R.color.holo_green_light
    }else{
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorToBar)
}

interface OnOptClick{
    fun invoke(opt: Int)
}
@BindingAdapter("onOptClick")
fun bindOnOptClick(textView: TextView, obj: OnOptClick){
    textView.setOnClickListener {
        obj.invoke(textView.text.toString().toInt())
    }
}


















