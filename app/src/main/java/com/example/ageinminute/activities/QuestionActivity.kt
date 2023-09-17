package com.example.ageinminute.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ageinminute.R
import com.example.ageinminute.adapters.OptionAdapter
import com.example.ageinminute.models.Question
import com.example.ageinminute.models.Quiz
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson

class QuestionActivity : AppCompatActivity() {

    var quizzes:MutableList<Quiz>? = null
    var questions:MutableMap<String,Question>? = null
    var index:Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        setUpFireStore()
        setEventListner()
    }

    private fun setEventListner() {
        val btnPrevious = findViewById<Button>(R.id.btnPrevious)
        val btnNext = findViewById<Button>(R.id.btnNext)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        btnPrevious.setOnClickListener(){
            index--
            bindView()
        }
        btnNext.setOnClickListener(){
            index++
            bindView()
        }
        btnSubmit.setOnClickListener(){
            val intent = Intent(this,ResultActivity::class.java)
            val json = Gson().toJson(quizzes!![0])
            Log.d("json",json)
            intent.putExtra("QUIZ",json)
            startActivity(intent)


        }
    }

    private fun setUpFireStore() {
        val date:String? = intent.getStringExtra("DATE")
        val firebaseStore = FirebaseFirestore.getInstance()
        if (date != null) {
            firebaseStore.collection("quizzes").whereEqualTo("title", date)
                .get()
                .addOnSuccessListener {
                    if(it != null && !it.isEmpty){
                        quizzes = it.toObjects(Quiz::class.java)
                        Log.d("data", quizzes.toString())
                        questions = quizzes!![0].questions
                        bindView()
                    }
                }
        }
    }


    private fun bindView() {
        val btnPrevious = findViewById<Button>(R.id.btnPrevious)
        val btnNext = findViewById<Button>(R.id.btnNext)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        btnPrevious.visibility = View.GONE
        btnNext.visibility = View.GONE
        btnSubmit.visibility = View.GONE


        if (index == questions!!.size) {
            if (index == 1){
                btnSubmit.visibility = View.VISIBLE
            }
            else {
                btnPrevious.visibility = View.VISIBLE
                btnSubmit.visibility = View.VISIBLE
            }
        }
        else if (index == 1) {
            btnNext.visibility = View.VISIBLE
        }
        else {
            btnPrevious.visibility = View.VISIBLE
            btnNext.visibility = View.VISIBLE
        }

        val question = questions!!["question$index"]

        question?.let {
            findViewById<TextView>(R.id.textViewDiscription).text = it.description
            val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewOptions)
            val adapter = OptionAdapter(this, it)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter
            recyclerView.setHasFixedSize(true)
        }
    }
}