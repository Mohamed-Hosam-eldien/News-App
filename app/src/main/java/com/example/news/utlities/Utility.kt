package com.example.news.utlities

import android.text.TextUtils
import android.util.Patterns
import com.example.news.models.User

class Utility {

    companion object{

        var USER : User? = null;


        fun isEmailValid(email: String) : Boolean{

            if (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                return false
            }
            return true
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