package com.crystal.android.geoquiz

import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {

    var currentIndex = 0
    var isCheater = false

    private var questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas,true),
        Question(R.string.question_asia,true)
    )

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToPrevious() {
        currentIndex = (currentIndex - 1 ) % questionBank.size
        if (currentIndex < 0) {
            currentIndex = questionBank.size - 1
        }
    }

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    /*var userCheckAnswer = mutableListOf(false,false,false,false,false,false)*/


}