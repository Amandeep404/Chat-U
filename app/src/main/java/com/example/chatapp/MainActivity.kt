package com.example.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatapp.login.ChatAuthStateListener
import com.example.chatapp.login.LogInFragment
import com.example.chatapp.main.ChatLandingFragment
import com.example.chatapp.utils.replaceFragment
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
            replaceFragment(R.id.fragmentContainerView, LogInFragment())
        }else{
            replaceFragment(R.id.fragmentContainerView, ChatLandingFragment())
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0){
            finish()
        }else{
            super.onBackPressed()
        }


    }
}


