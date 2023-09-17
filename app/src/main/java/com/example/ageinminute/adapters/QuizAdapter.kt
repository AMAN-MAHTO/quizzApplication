package com.example.ageinminute.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.ageinminute.R
import com.example.ageinminute.activities.QuestionActivity
import com.example.ageinminute.models.Quiz
import com.example.ageinminute.utils.ColorPicker
import com.example.ageinminute.utils.IconPicker
import java.util.zip.Inflater

class QuizAdapter(val context: Context, val quizzes:List<Quiz>):
    RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {

    inner class QuizViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textViewTitle = itemView.findViewById<TextView>(R.id.textViewTitle)
        val imageViewIcon = itemView.findViewById<ImageView>(R.id.imageViewIcon)
        val cardContainer = itemView.findViewById<CardView>(R.id.cardContainer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.quiz_item,parent,false)
        return QuizViewHolder(view)
    }

    override fun getItemCount(): Int {
        return quizzes.size
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.textViewTitle.text = quizzes[position].title
        holder.cardContainer.setCardBackgroundColor(Color.parseColor(ColorPicker.getColor()))
        holder.imageViewIcon.setImageResource(IconPicker.getIcon())
        holder.cardContainer.setOnClickListener(){
//            Toast.makeText(context,quizzes[position].title,Toast.LENGTH_SHORT).show()
            val intent = Intent(context, QuestionActivity::class.java)
            intent.putExtra("DATE",quizzes[position].title.toString())
            context.startActivity(intent)
        }
    }
}