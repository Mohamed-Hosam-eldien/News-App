package com.example.news.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.news.models.User


@Dao
 interface UserDao {

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertUser(user: User):Long

    @Query("SELECT * FROM user where userEmail= :userEmail AND userPassword= :userPassword ")
     suspend fun getUserFromDataBase(userEmail:String,userPassword:String):User



 }



