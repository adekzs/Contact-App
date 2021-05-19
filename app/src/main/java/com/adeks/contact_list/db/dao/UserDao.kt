package com.adeks.contact_list.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.adeks.contact_list.db.model.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    fun getUser(email : String, password : String) : LiveData<UserEntity>

    @Insert
    fun insertUser(userEntity: UserEntity)

    @Delete
    fun deleteUser(userEntity: UserEntity)

    @Update
    fun updateUser(userEntity: UserEntity)
}