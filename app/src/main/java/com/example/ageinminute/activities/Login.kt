package com.example.ageinminute.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.ageinminute.R
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        firebaseAuth = FirebaseAuth.getInstance()
        findViewById<Button>(R.id.btnLogin).setOnClickListener(){
            loginUser()
        }
        findViewById<TextView>(R.id.btnSignUp).setOnClickListener(){
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loginUser(){
        val email = findViewById<EditText>(R.id.editTextLoginEmail).text.toString()
        val password = findViewById<EditText>(R.id.editTextLoginPassword).text.toString()

        if (email.isBlank()||password.isBlank()){
            Toast.makeText(this,"Emai/Password can't be empty",Toast.LENGTH_SHORT).show()
            return
        }


        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(){
            if(it.isSuccessful){
                Toast.makeText(this,"Log in successful",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this,"Log in error",Toast.LENGTH_SHORT).show()
            }
        }
    }
}