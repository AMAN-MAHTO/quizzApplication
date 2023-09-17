package com.example.ageinminute.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.ui.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.example.ageinminute.R
import com.example.ageinminute.models.Question
import com.example.ageinminute.models.Quiz

class AnswerAdapter(val context: Context,val quiz: Quiz):
    RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder>() {
    private val questions: MutableMap<String, Question>
        get() = quiz.questions

    inner class AnswerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textViewQuestion = itemView.findViewById<TextView>(R.id.textViewQuestion)
        val textViewCorrectAnswer = itemView.findViewById<TextView>(R.id.textViewCorrectAnswer)
        val textViewUserAnswer = itemView.findViewById<TextView>(R.id.textViewUserAnswer)
        
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.answer_adapter_layout,parent,false)
        return AnswerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        Log.d("adapterQuiz",questions.toString())
        val answer = questions["question${position+1}"]!!.answer
        val userAnswer =questions["question${position+1}"]!!.userAnswer
        holder.textViewQuestion.text = questions["question${position+1}"]!!.description
        holder.textViewUserAnswer.text = "Your answer: $userAnswer"
        holder.textViewCorrectAnswer.text = "Answer: $answer"

    }
}