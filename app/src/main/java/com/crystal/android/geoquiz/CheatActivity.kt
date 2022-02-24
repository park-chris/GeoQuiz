package com.crystal.android.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

const val EXTRA_ANSWER_SHOWN = "com.crystal.android.geoquiz.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE = "com.crystal.android.geoquiz.answer_is_true"

class CheatActivity : AppCompatActivity() {

    private lateinit var answerTextView: TextView
    private lateinit var apiTextView: TextView
    private lateinit var showAnswerButton: Button

    private var answerIsTrue = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)



        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        answerTextView = findViewById(R.id.answer_text_view)
        apiTextView = findViewById(R.id.api_text_view)
        showAnswerButton = findViewById(R.id.show_anwser_button)

        var currentApi = Build.VERSION.SDK_INT.toString()

        apiTextView.text = "API 레벨 $currentApi"

        showAnswerButton.setOnClickListener {

            val answerText = when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            answerTextView.setText(answerText)
            setAnswerShownResult(true)

        }

    }


// MainActivity에서 사용가능하게 companion object 안에 CheatActivity가 필요로 하는 엑스트라 데이터를 갖는 인텐트 생성
// 동반 객체(companion object)를 사용하면 인스턴스를 생성하지 않고 동반 객체의 함수를 사용할수있음
    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }

    private fun setAnswerShownResult(isAnswerShown:Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }

}