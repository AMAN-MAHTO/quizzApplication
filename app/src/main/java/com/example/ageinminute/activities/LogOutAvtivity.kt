package com.example.ageinminute.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.ageinminute.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class LogOutAvtivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_out_avtivity)
        var firebaseAuth = FirebaseAuth.getInstance()
        findViewById<TextView>(R.id.textViewEmail).text = firebaseAuth.currentUser?.email

        findViewById<Button>(R.id.buttonSignOut).setOnClickListener(){
            firebaseAuth.signOut()
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }
    }
}