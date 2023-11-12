package com.code.quizapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.code.quizapp.MainActivity
import com.code.quizapp.R
import com.code.quizapp.util.Constant

class ResultActivity : AppCompatActivity() {

    private lateinit var textViewScore: TextView;
    private lateinit var textViewName: TextView;
    private lateinit var finishButton: Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        textViewName = findViewById(R.id.textview_name);
        textViewScore = findViewById(R.id.textview_score);
        finishButton = findViewById(R.id.finish_button);

        val totalQuestion = intent.getIntExtra(Constant.TOTAL_QUESTION, 0);
        val score = intent.getIntExtra(Constant.SCORE, 0);
        val username = intent.getStringExtra(Constant.USER_NAME);
        textViewScore.text = "Your score is $score out of $totalQuestion";
        textViewName.text = username;

        finishButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java);
            startActivity(intent);
        }
    }
}