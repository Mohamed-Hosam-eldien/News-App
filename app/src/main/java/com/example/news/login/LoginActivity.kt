package com.example.news.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.news.MainActivity
import com.example.news.R
import com.example.news.databinding.ActivityLoginBinding
import com.example.news.utlities.Utility
import com.example.news.register.RegisterActivity
import dagger.hilt.android.AndroidEntryPoint
import io.paperdb.Paper


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    lateinit var password: String
    lateinit var email: String


    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewmodel = loginViewModel

        binding.openRegister.setOnClickListener {
            goToRegisterScreen()
        }

        binding.btnLogin.setOnClickListener {

            if (isDataValidate()) {
                loginViewModel.getUserFromDataBase(email, password)
                loginViewModel.getUser.observe(this) {
                    if (it != null) {
                        Paper.book().write("user", it)
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        showUserNotExistDialog()
                    }
                }
            }

        }

    }

    private fun isDataValidate(): Boolean {
        val password = binding.userPasswordLogin.text.toString()
        val email = binding.userEmailLogin.text.toString()
        if (!Utility.isPassValid(password) || password.isEmpty()) {
            binding.userPasswordLogin.error = "Write correct password"
            return false
        }

        if (!Utility.isEmailValid(email) || email.isEmpty()) {
            binding.userEmailLogin.error = "Write correct email"
            binding.userEmailLogin.error = "Write correct email"
            return false
        }
        return true
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
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }
}
