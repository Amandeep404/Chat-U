package com.example.chatapp.login

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.chatapp.R
import com.example.chatapp.repository.UserRepository
import com.example.chatapp.utils.mapFromFirebaseUser
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth

class LogInFragment: Fragment() {

    private var chatAuthStateListener : ChatAuthStateListener? = null
    private val providers = arrayListOf(
        AuthUI.IdpConfig.EmailBuilder().build(),
        AuthUI.IdpConfig.PhoneBuilder().build(),
        AuthUI.IdpConfig.GoogleBuilder().build()
    )

    //In onAttach(), we are keeping a reference of ChatAuthStateListener
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ChatAuthStateListener){
            chatAuthStateListener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doLogIn()
    }

    private fun doLogIn() {

        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setLogo(R.drawable.chat_u_logo)
            .setTheme(R.style.ChatAppLogin)
            .setIsSmartLockEnabled(false)
            .setTosAndPrivacyPolicyUrls(
                resources.getString(R.string.terms_of_service),
                resources.getString(R.string.privacy_policy)
            )
            .build()

        signInLauncher.launch(signInIntent)

    }

    private val signInLauncher =registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ){res ->
        this.onSignInResult(res)
    }

    private fun onSignInResult(res: FirebaseAuthUIAuthenticationResult) {
        if (res.resultCode == AppCompatActivity.RESULT_OK){
            //On success add this user to chat user firebase database
            FirebaseAuth.getInstance().currentUser?.also {user ->
                UserRepository().createOrUpdateUser(
                    mapFromFirebaseUser(user)
                )
                chatAuthStateListener?.onAuthStateChanged()
            }?: showError(
                requireActivity(),
                "Something went wrong"
            )
        }else{
           // Sign in failed. If response is null the user canceled the
           // sign-in flow using the back button. Otherwise check
           // response.getError().getErrorCode() and handle the error.

            if (res.idpResponse == null){
                requireActivity().finish()
            }else{
                res.idpResponse?.error?.message?.let{
                    showError(requireActivity(), it)
                }
            }
        }

    }

    private fun showError(activity : Activity, errorMsg: String) {
        val builder = AlertDialog.Builder(activity)
        builder.apply {
            setPositiveButton(
               "Retry"
            ) { dialog, id ->
                doLogIn()
            }
            setNegativeButton(
                "Exit"
            ) { dialog, id ->
                activity.finish()
            }
            setMessage(errorMsg)
        }
        // Create the AlertDialog
        builder.create().show()
    }
}