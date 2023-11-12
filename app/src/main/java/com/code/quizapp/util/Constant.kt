package com.code.quizapp.util

import com.code.quizapp.R
import com.code.quizapp.model.Question


object Constant {

    const val USER_NAME = "user";
    const val TOTAL_QUESTION = "total_question"
    const val SCORE = "correct_answers";
    fun getQuestion(): MutableList<Question> {

        val questions = mutableListOf<Question>()

        val question1 = Question(
            1111,
            "What country does the flag belong to?",
            R.drawable.palestine,
            "India",
            "Italy",
            "Iran",
            "Palestine",
            4
        )


        val question2 = Question(
            2222,
            "Which planet is known as the Red Planet?",
            R.drawable.mars,
            "Mars",
            "Venus",
            "Jupiter",
            "Moon",
            1

        );
        val question3 = Question(
            3,
            "What is the largest animal on Earth?",
            R.drawable.animal,
            "Elephant",
            "Whale Shark",
            "Blue Whale",
            "Dog",
            1

        )

        val question4 = Question(
            4,
            "What is the chemical symbol for water?",
            R.drawable.water,
            "HO",
            "H2O",
            "CO2",
            "H20CO2",
            2
        )

        questions.addAll(arrayOf(question1, question2, question3, question4));

        return questions;
    }

}

