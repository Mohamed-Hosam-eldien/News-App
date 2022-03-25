package com.example.news.login

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.news.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val loginViewModel:LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        loginViewModel.getUserFromDataBase("doaa","000")
            .observe(this) {
                if(it!=null){
                    Log.e(TAG, "onCreate:  if ", )
                 }
                else{
                    showUserNotExistDialog()
                }
            }
    }

    private fun showUserNotExistDialog() {
        val alertDialog = AlertDialog.Builder(this)

        alertDialog.apply {
            setIcon(R.drawable.ic_baseline_error_outline_24)
            setTitle("Login Failed")
            setMessage("user is not exist, please register and try again")
            setPositiveButton("Register") { _, _ ->
                goToRegisterScreen()
            }
        }.create().show()
    }

    private fun goToRegisterScreen() {
        Log.d(TAG, "goToRegisterScreen: ")
        // intent handle
        finish()
    }
}