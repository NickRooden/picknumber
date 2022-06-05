package com.nickrooden.picknumber.presentation

import android.widget.ImageView
import android.widget.TextView
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