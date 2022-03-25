package com.example.news.login

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.news.MainActivity
import com.example.news.R
import com.example.news.databinding.ActivityLoginBinding
import com.example.news.utlities.Utility
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewmodel = loginViewModel

        binding.btnLogin.setOnClickListener {

            if (isDataValidate()) {
                loginViewModel.getUserFromDataBase("doaa", "000")
                    .observe(this) {
                        if (it != null) {

                            Utility.USER = it
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)

                        } else {
                            showUserNotExistDialog()
                        }
                    }
            }

        }


    }

    fun isDataValidate(): Boolean {
        val password = binding.edtPassword.text.toString()
        val email = binding.edtEmail.text.toString()
        if (!Utility.isPassValid(password) || password.isEmpty()) {
            binding.edtPassword.error = "Write correct password"
            return false
        }

        if (!Utility.isEmailValid(email) || email.isEmpty()) {
            binding.edtEmail.error = "Write correct email"
            return false
        }
        return true
    }

    private fun goToRegisterScreen() {
        Log.d(TAG, "goToRegisterScreen: ")
        // intent handle
        finish()
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


}