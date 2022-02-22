package com.crystal.android.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var previousButton: Button
    private lateinit var questionTextView: TextView

    private var questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas,true),
        Question(R.string.question_asia,true)
    )

    private var userCheckAnswer = mutableListOf(false,false,false,false,false,false)

    private var currentIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

/*        val provider: ViewModelProvider = ViewModelProvider(this)
        val quizViewModel = provider.get(QuizViewModel::class.java)
        아래 한줄로 축약 */
        val quizViewModel = ViewModelProvider(this).get(QuizViewModel::class.java)
        Log.d(TAG, "Got a QuizViewModel: $quizViewModel")

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
            currentIndex = (currentIndex - 1 ) % questionBank.size
            if (currentIndex < 0) {
                currentIndex = questionBank.size - 1
            }
            updateQuestion()
        }

        nextButton.setOnClickListener {

            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        questionTextView.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        updateQuestion()
    }

    //    현재 인덱스에 따라 문제질문을 업데이트해주는 함수
    private fun updateQuestion(){
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)

        if (userCheckAnswer[currentIndex]) {
            trueButton.isEnabled = false
            falseButton.isEnabled = false
        } else {
            trueButton.isEnabled = true
            falseButton.isEnabled = true
        }

    }

    //    리스트에 있는 답과 사용자가 찍은 답이 일치하는지 체크하는 함수
    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        if (userAnswer == correctAnswer) {
            userCheckAnswer[currentIndex] = true
            trueButton.isEnabled = false
            falseButton.isEnabled = false
        }

        if (currentIndex == 5) {

            var totalScore: Double = 0.0

            for (i in userCheckAnswer) {
                if (i){
                    totalScore++
                }
            }

            val totalPercentage:Double = totalScore / userCheckAnswer.size * 100

            Toast.makeText(this, totalPercentage.toString(), Toast.LENGTH_LONG).show()

        } else {
            val messageResId = if (userAnswer == correctAnswer) {
                R.string.correct_toast
            } else {
                R.string.incorrect_toast
            }

            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
        }

    }

}


