package com.adeks.contact_list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.adeks.contact_list.databinding.ActivitySignInBinding
import com.adeks.contact_list.db.UserRepository
import com.adeks.contact_list.db.model.UserEntity

class SignInActivity : AppCompatActivity() {
    private val TAG = "SignInActivity"
    private lateinit var binding : ActivitySignInBinding
    private lateinit var email : TextView
    private lateinit var password : TextView
    private lateinit var userRepository: UserRepository
    private lateinit var signInBtn : Button
    private lateinit var registerBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Sign In"
        init()
        registerBtn.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        signInBtn.setOnClickListener{
            val user : LiveData<UserEntity> = userRepository.getUser(email.text.toString(), password.text.toString())
            user.observe(this, { userDet ->
                if (userDet != null) {
                    Log.d(TAG, "onCreate: The user is $userDet")
                    val int  = Intent(this, ContactCategoryActivity::class.java)
                    startActivity(int)
                } else {
                    Toast.makeText(
                        this,
                        "user with ${email.text.toString()} not found",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
    }

    private fun init() {
        email = binding.emailSigninEt
        password = binding.passwordSigninEt
        signInBtn = binding.signIn
        registerBtn = binding.register
        userRepository = UserRepository(this)
    }
}