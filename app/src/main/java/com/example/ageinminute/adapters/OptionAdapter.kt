package com.example.ageinminute.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ageinminute.R
import com.example.ageinminute.models.Question
import java.util.zip.Inflater

class OptionAdapter(val context: Context,val question: Question):
    RecyclerView.Adapter<OptionAdapter.OptionViewHolder>() {

    private val options = listOf<String>(question.option1,question.option2,question.option3,question.option4)

    inner class OptionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val optionView: TextView = itemView.findViewById<TextView>(R.id.textViewQuizOption)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.quiz_option,parent,false)
        return OptionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return options.size
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.optionView.text = options[position]
        holder.itemView.setOnClickListener(){
            question.userAnswer = options[position]
            notifyDataSetChanged()
        }
        if (question.userAnswer == options[position]){
            holder.itemView.setBackgroundResource(R.drawable.option_item_selected_bg)
        }
        else{
            holder.itemView.setBackgroundResource(R.drawable.option_item_bg)
        }
    }

}