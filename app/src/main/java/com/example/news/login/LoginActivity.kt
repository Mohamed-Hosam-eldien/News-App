package com.example.news.login

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.example.news.MainActivity
import com.example.news.utlities.Utility
import com.example.news.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val loginViewModel:LoginViewModel by viewModels()
    private lateinit var binding : ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewmodel = loginViewModel



        binding.btnLogin.setOnClickListener {

            if (isDataValidate()){
                loginViewModel.getUserFromDataBase("doaa","000")
                    .observe(this) {
                        if(it!=null){

                          Utility.USER = it
                            var intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)

                        }
                        else{
                            //Log.e(TAG, "onCreate:  else ", )
                            Toast.makeText(this, "Not correct", Toast.LENGTH_SHORT).show()
                        }
                    }
            }

        }




    }

    fun isDataValidate() : Boolean{
        var password = binding.edtPassword.text.toString()
        var email = binding.edtEmail.text.toString()
        if (!Utility.isPassValid(password) || password.isEmpty()){
            binding.edtPassword.error = "Write correct password"
            return false
        }

        if (!Utility.isEmailValid(email) || email.isEmpty()){
            binding.edtEmail.error = "Write correct email"
            return false
        }
        return true
    }
}