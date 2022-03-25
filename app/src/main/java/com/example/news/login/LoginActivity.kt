package com.example.news.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.news.Utility
import com.example.news.databinding.ActivityLoginBinding
import kotlinx.coroutines.delay

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var email = binding.edtEmail.text.toString()
        var pass = binding.edtPassword.text.toString()

        binding.btnLogin.setOnClickListener {
            if (Utility.isEmailValid(email))
            {
                binding.edtEmail.error = "write correct email"
            }
            if (Utility.isPassValid(pass))
            {
                binding.edtEmail.error = "write correct pass"
            }
        }


    }
}