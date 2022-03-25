package com.example.news.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.news.MainActivity
import com.example.news.R
import com.example.news.login.LoginActivity
import com.example.news.models.User
import com.example.news.register.RegisterActivity
import io.paperdb.Paper

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Paper.init(this)
        val user: User? = Paper.book().read("user")
        Handler(Looper.myLooper()!!).postDelayed({
        if (user == null){
            val intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        else{
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        }, 2000)
    }
}