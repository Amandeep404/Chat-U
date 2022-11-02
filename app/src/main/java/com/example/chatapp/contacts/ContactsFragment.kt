package com.example.chatapp.contacts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.R
import com.example.chatapp.chat.ChatScreenFragment
import com.example.chatapp.repository.ChatUser
import com.example.chatapp.utils.mapFromFirebaseUser
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.getInstance
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_contacts.*

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
            .setLifecycleOwner(this)
            .setQuery(userQuery, ChatUser::class.java)
            .build()
        adapter = ContactsAdapter(
            options,
            object : ContactsAdapter.OnItemClickListener{
                override fun onItemClick(chatWith: ChatUser) {
                    // Show ChatScreenFragment, where user can start chatting
                    FirebaseAuth.getInstance().currentUser?.let {user ->
                        val currentUser = mapFromFirebaseUser(user)
                            // TODO ChatScreenFragment.getInstance()
                    }
                }
            }

        )

        //setting up recycleView
        val layoutManager = LinearLayoutManager(requireContext())
        contactsRecyclerView.layoutManager = layoutManager
        contactsRecyclerView.setHasFixedSize(true)
        contactsRecyclerView.adapter =  adapter
        contactsRecyclerView.addItemDecoration(
            DividerItemDecoration(
                contactsRecyclerView.context,
                layoutManager.orientation
            )
        )
    }
}