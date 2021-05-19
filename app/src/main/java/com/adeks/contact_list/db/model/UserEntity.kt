package com.adeks.contact_list.db.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "user",
        indices = [Index(value = ["email"],
            unique = true
        )]
    )
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val firstName : String,
    val lastName : String,
    val email : String,
    val password : String
)
