package com.example.news.login

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import com.example.news.R
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import java.util.*

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
                    Log.e(TAG, "onCreate:  else ", )

                }
            }
    }
}