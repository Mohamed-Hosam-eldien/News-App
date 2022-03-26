package com.example.news.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.news.MainActivity
import com.example.news.R
import com.example.news.databinding.ActivityRegisterBinding
import com.example.news.login.LoginActivity
import com.example.news.models.User
import com.example.news.utlities.Utility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private val registerViewModel: RegisterViewModel by viewModels()
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewModel = registerViewModel

        binding.openLogin.setOnClickListener {
            goToLoginScreen()
        }
        binding.btnRegister.setOnClickListener {
            if (isDataValid()) {
                registerViewModel.insertUser(creatUser())
                registerViewModel.getUser.observe(this) {

                    if (it != null) {
                        Utility.USER = it
                        goToMainScreen()
                    } else {
                        showUserNotExistDialog()
                    }
                }
            }


        }


    }

    private fun creatUser(): User {

        val password = binding.userPassword.text.toString()
        val userName = binding.userName.text.toString()
        val email = binding.userEmail.text.toString()
        val phone = binding.userPhone.text.toString()

        val user = User(email, userName, password, phone)
        return user
    }

    private fun isDataValid(): Boolean {

        val password = binding.userPassword.text.toString()
        val userName = binding.userName.text.toString()
        val email = binding.userEmail.text.toString()
        val phone = binding.userPhone.text.toString()
        val confirm = binding.confirmPassword.text.toString()

        if (!Utility.isPassValid(password) ||password.isEmpty()) {
            binding.userPassword.error = "Write correct password"
            return false
        }

        if(password!=confirm){
            binding.confirmPassword.error="password don't  match"
            return false
        }

        if (!Utility.isEmailValid(email) || email.isEmpty()) {
            println("not valid email")
            binding.userEmail.error = "Write correct email"
            return false
        }


        if (!Utility.isUsernameValid(userName) || userName.isEmpty()) {
            binding.userName.error = "Write correct user name"
            return false
        }

        if (!Utility.isPhoneValid(phone) || phone.isEmpty()) {
            binding.userPhone.error = "Write correct phone number"
            return false
        }

        return true
    }

    private fun showUserNotExistDialog() {
        val alertDialog = AlertDialog.Builder(this)

        alertDialog.apply {
            setIcon(R.drawable.ic_baseline_error_outline_24)
            setTitle("Register Failed")
            setMessage("please  try again")
            setPositiveButton("ok") { _, _ ->

            }
        }.create().show()


    }

    private fun goToLoginScreen() {
        var intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToMainScreen() {
        var intent=Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}