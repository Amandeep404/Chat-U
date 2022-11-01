package com.example.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatapp.login.ChatAuthStateListener
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity(), ChatAuthStateListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showFragment()
    }
    override fun onAuthStateChanged() {
        if (FirebaseAuth.getInstance().currentUser!=null){
            showFragment()
        }
    }

    private fun showFragment() {
        if (FirebaseAuth.getInstance().currentUser == null){

        }
    }
}


