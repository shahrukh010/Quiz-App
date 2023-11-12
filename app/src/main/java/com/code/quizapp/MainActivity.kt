package com.code.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.code.quizapp.ui.QuestionActivity
import com.code.quizapp.util.Constant

class MainActivity : AppCompatActivity() {
    private lateinit var startButton: Button;
    private lateinit var editTextName: EditText;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton = findViewById(R.id.button_start);
        editTextName = findViewById(R.id.input_text);

        startButton.setOnClickListener {

            if (!editTextName.text.isEmpty()) {

                val intent = Intent(this@MainActivity, QuestionActivity::class.java);
                intent.putExtra(Constant.USER_NAME, editTextName.text.toString());
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this@MainActivity, "Please enter name", Toast.LENGTH_LONG).show();
            }
        }
    }
}