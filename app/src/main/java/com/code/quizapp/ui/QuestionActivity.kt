package com.code.quizapp.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.code.quizapp.R
import com.code.quizapp.model.Question
import com.code.quizapp.util.Constant
import kotlin.math.sign

class QuestionActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var progressBar: ProgressBar;
    private lateinit var textViewProgress: TextView;
    private lateinit var textViewQuestion: TextView;
    private lateinit var flagImage: ImageView;
    private lateinit var optionOne: TextView;
    private lateinit var optionTwo: TextView;
    private lateinit var optionThree: TextView;
    private lateinit var optionFour: TextView;
    private lateinit var questionList: MutableList<Question>;
    private var questionsCounter = 0;
    private lateinit var checkButton: Button;
    private var selectedAnswer = 0;
    private lateinit var currentQuestion: Question;
    private var answered = false;
    private lateinit var name: String;
    private var score = 0;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        progressBar = findViewById(R.id.progressBar);
        textViewProgress = findViewById(R.id.text_view_progress);
        textViewQuestion = findViewById(R.id.question_text_view);
        flagImage = findViewById(R.id.image_flag);
        optionOne = findViewById(R.id.text_view_option_one);
        optionTwo = findViewById(R.id.text_view_option_two);
        optionThree = findViewById(R.id.text_view_option_three);
        optionFour = findViewById(R.id.text_view_option_four);
        checkButton = findViewById(R.id.button_check)
        questionList = Constant.getQuestion();

        optionOne.setOnClickListener(this);
        optionTwo.setOnClickListener(this);
        optionThree.setOnClickListener(this);
        optionFour.setOnClickListener(this);
        checkButton.setOnClickListener(this);
        showNextQuestion();

        if (intent.hasExtra(Constant.USER_NAME)) {
            name = intent.getStringExtra(Constant.USER_NAME)!!;
        }
    }

    private fun resetOption() {

        val options = mutableListOf<TextView>();
        options.add(optionOne);
        options.add(optionTwo)
        options.add(optionThree);
        options.add(optionFour);

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"));
            option.typeface = Typeface.DEFAULT;
            option.background =
                ContextCompat.getDrawable(this, R.drawable.default_option_border_bg);
        }
    }

    private fun selectedOption(viewText: TextView, selectedNumber: Int) {

        resetOption();
        selectedAnswer = selectedNumber;
        viewText.setTextColor(Color.parseColor("#363A43"));
        viewText.setTypeface(viewText.typeface, Typeface.BOLD);
        viewText.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border)
    }


    private fun showNextQuestion() {
        resetOption()
        if (questionsCounter < questionList.size) {
            val question = questionList[questionsCounter]
            flagImage.setImageResource(question.image)
            progressBar.progress = questionsCounter
            textViewProgress.text = "$questionsCounter/${progressBar.max}"
            textViewQuestion.text = question.question
            optionOne.text = question.optionOne
            optionTwo.text = question.optionTwo
            optionThree.text = question.optionThree
            optionFour.text = question.optionFour;

            currentQuestion = questionList[questionsCounter]

            if (questionsCounter == questionList.size - 1) {
                checkButton.text = "FINISH"
            } else {
                checkButton.text = "CHECK"
            }

            questionsCounter++
            answered = false
        } else {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra(Constant.USER_NAME, name);
            intent.putExtra(Constant.SCORE, score);
            intent.putExtra(Constant.TOTAL_QUESTION, questionList.size);

            startActivity(intent)
        }
    }


    override fun onClick(view: View?) {

        when (view?.id) {

            R.id.text_view_option_one -> {
                selectedOption(optionOne, 1)
            }

            R.id.text_view_option_two -> {

                selectedOption(optionTwo, 2);
            }

            R.id.text_view_option_three -> {
                selectedOption(optionThree, 3)
            }

            R.id.text_view_option_four -> {
                selectedOption(optionFour, 4);
            }

            R.id.button_check -> {

                if (!answered) {

                    checkAnswer();
                } else {
                    showNextQuestion();
                }
                selectedAnswer = 0;
            }

        }
    }

    private fun checkAnswer() {
        answered = true;
        if (selectedAnswer == currentQuestion.correctAnswer) {

            score++;
            highlightAnswer(selectedAnswer);
        } else {

            when (selectedAnswer) {

                1 -> optionOne.background = ContextCompat.getDrawable(
                    this, R.drawable.wrong_option_border_bg
                )

                2 -> optionTwo.background = ContextCompat.getDrawable(
                    this, R.drawable.wrong_option_border_bg
                )

                3 -> optionThree.background = ContextCompat.getDrawable(
                    this, R.drawable.wrong_option_border_bg
                )

                4 -> optionFour.background = ContextCompat.getDrawable(
                    this, R.drawable.wrong_option_border_bg
                )
            }
            highlightAnswer(currentQuestion.correctAnswer);
        }
        checkButton.text = "NEXT";

    }

    private fun highlightAnswer(answer: Int) {

        when (answer) {

            1 -> optionOne.background =
                ContextCompat.getDrawable(
                    this,
                    R.drawable.correct_option_border_bg
                )

            2 -> optionTwo.background =
                ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg);

            3 -> optionThree.background =
                ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg);

            4 -> optionFour.background =
                ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg);
        }
    }
}