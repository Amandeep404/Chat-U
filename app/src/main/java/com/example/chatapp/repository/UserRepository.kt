package com.example.chatapp.repository

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UserRepository {
    fun createOrUpdateUser(user : ChatUser){
        // Using firebase database KTX Lib
        val database = Firebase.database
        val userDbRef = database.getReference("users")
        val userNodeRef = userDbRef.child(user.uid)
        userNodeRef.setValue(user)
    }
}