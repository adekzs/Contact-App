package com.adeks.contact_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.adeks.contact_list.databinding.ActivityRegisterBinding
import com.adeks.contact_list.db.UserRepository
import com.adeks.contact_list.db.model.UserEntity

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private lateinit var firstname : TextView
    private lateinit var lastname : TextView
    private lateinit var email : TextView
    private lateinit var password : TextView
    private lateinit var confirmpass : TextView
    private lateinit var addUserBtn : Button

    private lateinit var userRepository: UserRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Register"
        init()
        addUserBtn.setOnClickListener{
            if (!checkallFields()) {
                Toast.makeText(this, "All Fields should be filled", Toast.LENGTH_SHORT).show()
            } else if (password.text.toString() != confirmpass.text.toString()) {
                Toast.makeText(
                    this,
                    "Password is not the same as confirm password",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else{
                val user = UserEntity(firstName = firstname.text.toString(), lastName = lastname.text.toString(), password = password.text.toString(), email = email.text.toString())
                userRepository.addUser(user)
                finish()
            }
        }
    }

    private fun checkallFields(): Boolean {
        if(firstname.text.isNullOrBlank() || lastname.text.isNullOrBlank() || email.text.isNullOrBlank()
            || password.text.isNullOrBlank() || confirmpass.text.isNullOrBlank()) {
            return false
        }
        return true
    }

    private fun init() {
        firstname = binding.firstnameEt
        lastname = binding.lastNameEt
        email = binding.emailEt
        password = binding.passwordEt
        confirmpass = binding.confPasswordEt
        addUserBtn = binding.addUser

        userRepository = UserRepository(this)
    }
}