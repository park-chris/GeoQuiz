package com.crystal.android.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

// Bundle 객체에 저장될 데이터의 키
private const val KEY_INDEX = "index"

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var previousButton: Button
    private lateinit var questionTextView: TextView
    private val quizViewModel:QuizViewModel by lazy {
        ViewModelProvider(this).get(QuizViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


// Bundle 객체에 저장된 값을 확인해 값이 있으면 값을 currentIndex에 저장
// 그렇지 않고 키("index")가 Bundle 객체에 없거나,
// Bundle 객체 참조가 null이면 currentIndex의 값을 0을 설정
        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0

        if (currentIndex != quizViewModel.currentIndex) {
            quizViewModel.currentIndex = currentIndex
        }


        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        previousButton = findViewById(R.id.previous_button)
        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener { view: View ->

            checkAnswer(true)

        }

        falseButton.setOnClickListener { view: View ->

            checkAnswer(false)

        }



        previousButton.setOnClickListener {
            quizViewModel.moveToPrevious()
            updateQuestion()
        }

        nextButton.setOnClickListener {

            quizViewModel.moveToNext()
            updateQuestion()
        }

        questionTextView.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()
        }

        updateQuestion()
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.d("MainActivity", "onSaveInstanceState")
        savedInstanceState.putInt(KEY_INDEX, quizViewModel.currentIndex)
    }

    //    현재 인덱스에 따라 문제질문을 업데이트해주는 함수
    private fun updateQuestion(){
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)

/*        if (userCheckAnswer[currentIndex]) {
            trueButton.isEnabled = false
            falseButton.isEnabled = false
        } else {
            trueButton.isEnabled = true
            falseButton.isEnabled = true
        }*/

    }

    //    리스트에 있는 답과 사용자가 찍은 답이 일치하는지 체크하는 함수
    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer

/*        if (userAnswer == correctAnswer) {
            userCheckAnswer[currentIndex] = true
            trueButton.isEnabled = false
            falseButton.isEnabled = false
        }*/

/*        if (currentIndex == 5) {

            var totalScore: Double = 0.0

            for (i in userCheckAnswer) {
                if (i){
                    totalScore++
                }
            }

            val totalPercentage:Double = totalScore / userCheckAnswer.size * 100

            Toast.makeText(this, totalPercentage.toString(), Toast.LENGTH_LONG).show()

        }*/

        val messageResId = if (userAnswer == correctAnswer) {
                R.string.correct_toast
            } else {
                R.string.incorrect_toast
            }

            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()


    }

}


