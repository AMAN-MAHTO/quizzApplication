package com.example.ageinminute.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.ageinminute.R
import com.google.firebase.auth.FirebaseAuth

class LoginIntro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_intro)

        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null){
            redirect("Main")
        }

        findViewById<Button>(R.id.btnGetStarted).setOnClickListener(){
            redirect("Login")
        }

    }

    private fun redirect(page:String){
        val intent = when(page){
            "Login" -> Intent(this, Login::class.java)
            "Main" -> Intent(this, MainActivity::class.java)
            else -> throw Exception("no path exits")
        }
        startActivity(intent)
        finish()
    }
}