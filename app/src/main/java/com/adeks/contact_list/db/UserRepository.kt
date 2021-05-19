package com.adeks.contact_list.db

import android.content.Context
import androidx.lifecycle.LiveData
import com.adeks.contact_list.db.model.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UserRepository(context: Context) {
    private val db = UserDatabase.invoke(context)

    fun getUser(email : String, password : String) : LiveData<UserEntity> = db.userDao().getUser(email, password)

    fun addUser(userEntity: UserEntity) {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                db.userDao().insertUser(userEntity)
            }
        }
    }
}