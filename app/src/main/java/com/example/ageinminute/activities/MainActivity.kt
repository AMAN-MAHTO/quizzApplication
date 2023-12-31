package com.example.ageinminute.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ageinminute.R
import com.example.ageinminute.adapters.QuizAdapter
import com.example.ageinminute.models.Quiz
import com.google.android.material.navigation.NavigationBarMenu
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    var quizList = mutableListOf<Quiz>()
    lateinit var adapter: QuizAdapter
    lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Toolbar>(R.id.topAppBar)
        setUpViews()

    }


    fun setUpViews(){
        setUpFireStore()
        setUpDrawerLaout()
        setUpRecyclerView()
    }

    private fun setUpFireStore() {
        firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("quizzes")
        collectionReference.addSnapshotListener { value, error ->
            if (value == null || error != null){
                Toast.makeText(this,"Error fecting the data",Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            Log.d("DATA",value.toObjects(Quiz::class.java).toString())
            quizList.clear()
            quizList.addAll(value.toObjects(Quiz::class.java))
            adapter.notifyDataSetChanged()

        }
    }

    private fun setUpRecyclerView() {
        adapter = QuizAdapter(this, quizList )
        val recyclerView=findViewById<RecyclerView>(R.id.recyclerCardHolderView)
        recyclerView.layoutManager = GridLayoutManager(this,2)
        recyclerView.adapter = adapter

    }

    fun setUpDrawerLaout(){
        val toolbar  = findViewById<Toolbar>(R.id.topAppBar)
        val navigationView = findViewById<NavigationView>(R.id.navigationView)
        val mainDrawer = findViewById<DrawerLayout>(R.id.mainDrawer)
        setSupportActionBar(toolbar)
        actionBarDrawerToggle = ActionBarDrawerToggle(this,mainDrawer,
            R.string.app_name,
            R.string.app_name
        )
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.btnProfile -> {
                    Log.d("navigation", "itme selected")
                    val intent = Intent(this, LogOutAvtivity::class.java)
                    startActivity(intent)
                    mainDrawer.closeDrawers()
                    true
                }
                else -> {
                    true
                }
            }

        }



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
