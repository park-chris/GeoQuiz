package com.crystal.android.geoquiz

import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {

    var currentIndex = 0

    private var questionBank = mutableListOf(
        Question(R.string.question_australia, true, cheat = false),
        Question(R.string.question_oceans, true, cheat = false),
        Question(R.string.question_mideast, false, cheat = false),
        Question(R.string.question_africa, false, cheat = false),
        Question(R.string.question_americas,true, cheat = false),
        Question(R.string.question_asia,true, cheat = false)
    )

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    val isCheater: Boolean
        get() = questionBank[currentIndex].cheat

    fun moveToPrevious() {
        currentIndex = (currentIndex - 1 ) % questionBank.size
        if (currentIndex < 0) {
            currentIndex = questionBank.size - 1
        }
    }

    fun changeCheat() {
        questionBank[currentIndex] = Question(currentQuestionText, currentQuestionAnswer, cheat =  true)
    }

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }



}