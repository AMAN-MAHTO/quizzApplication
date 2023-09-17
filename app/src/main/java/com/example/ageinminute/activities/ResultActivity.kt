package com.example.ageinminute.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ageinminute.R
import com.example.ageinminute.adapters.AnswerAdapter
import com.example.ageinminute.models.Quiz
import com.google.gson.Gson
import kotlinx.coroutines.channels.ReceiveChannel

class ResultActivity : AppCompatActivity() {
    lateinit var quiz:Quiz
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)


        setUpView()
        countScore()


    }

    private fun countScore() {
        var score=0
        var maxScore = quiz.questions.size * 10
        for(entry in quiz.questions.entries){
            val question = entry.value
            if (question.answer == question.userAnswer){
                score += 10
            }
        }
        findViewById<TextView>(R.id.textViewScore).text = "Your Score: $score/$maxScore"
    }

    private fun setUpView() {
        val quizData = intent.getStringExtra("QUIZ")
        quiz = Gson().fromJson<Quiz>(quizData,Quiz::class.java)
        val adapter = AnswerAdapter(this,quiz)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewAnswers)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
            }



}