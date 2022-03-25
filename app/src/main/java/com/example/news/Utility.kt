package com.example.news

import android.text.TextUtils
import android.util.Patterns

class Utility {

    companion object{
        fun isEmailValid(email: String) : Boolean{
            return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun isPassValid(pass: String) : Boolean{

            if (pass.length in 5..21){
                return false
            }
            return true
        }

        fun isPhoneValid(phone: String) : Boolean{

            if (phone.length != 11){
                return false
            }
            return true
        }

        fun isUsernameValid(user: String) : Boolean{

            if (user.length in 3..13){
                return false
            }
            return true
        }
    }

}