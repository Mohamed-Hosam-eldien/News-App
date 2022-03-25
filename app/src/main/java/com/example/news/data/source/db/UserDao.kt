package com.example.news.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.news.models.User


@Dao
 interface UserDao {

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertUser(user: User):Long

    @Query("SELECT * FROM user where userEmail= :userEmail AND userPassword= :userPassword ")
     suspend fun getUserFromDataBase(userEmail:String,userPassword:String):User

    @Query("Update user set userPassword = :userPassword where userEmail = :userEmail")
    suspend fun updateUserFromDataBase(userPassword: String, userEmail: String)

}



