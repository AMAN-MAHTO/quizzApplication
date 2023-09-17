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

class SignUp : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        firebaseAuth = FirebaseAuth.getInstance()

        val email = findViewById<EditText>(R.id.editTextSignUpEmail)
        val password = findViewById<EditText>(R.id.editTextSignUpPassword)
        val confPassword = findViewById<EditText>(R.id.editTextSignUpConformPassword)
        val signUpButton = findViewById<Button>(R.id.btnSignUp)
        signUpButton.setOnClickListener(){
            signupUser(email.text.toString(),password.text.toString(),confPassword.text.toString())
        }
        findViewById<TextView>(R.id.btnLogin).setOnClickListener(){
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun signupUser(email:String,password:String,confPassword:String){

        if(email.isBlank() || password.isBlank() || confPassword.isBlank()){
            Toast.makeText(this,"email and password can't be empty",Toast.LENGTH_SHORT).show()
            return
        }

        if(password != confPassword){
            Toast.makeText(this,"Password and conform password do not match",Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Toast.makeText(this,"successful",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this,"ERROr",Toast.LENGTH_SHORT).show()
                }
            }
    }
}