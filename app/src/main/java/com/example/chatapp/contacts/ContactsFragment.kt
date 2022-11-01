package com.example.chatapp.contacts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.chatapp.R
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase

class ContactsFragment : Fragment(R.layout.fragment_contacts) {
    private val userQuery = FirebaseDatabase.getInstance().getReference("users").limitToLast(50)
    //userQuery will be used to read data from Firebase Realtime users data which we created and added user info before.

    private lateinit var adapter : ContactsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRecycleView()
    }

    private fun prepareRecycleView() {
        val options = FirebaseRecyclerOptions.Builder<ChatUser>()

    }
}