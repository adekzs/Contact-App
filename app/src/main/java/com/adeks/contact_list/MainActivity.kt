package com.adeks.contact_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import com.adeks.contact_list.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var contactAdapter : ContactAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getArguments()
        setUpData(binding)
    }

    private fun getArguments() {
        title = intent.getStringExtra(CategoryAdapter.categoryName)
    }

    private fun setUpData(binding: ActivityMainBinding) {

        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.add_contact_dialog,null)
        builder.setView(view)
        binding.contactsRv.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        val name = view.findViewById<TextView>(R.id.nameEt)
        val no = view.findViewById<TextView>(R.id.phoneEt)
        val saveBtn = view.findViewById<TextView>(R.id.save_btn)

        contactAdapter = ContactAdapter()
        binding.contactsRv.adapter = contactAdapter
        no.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                saveBtn.isEnabled = s?.length == 11
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        val alertDialog = builder.create()
        saveBtn.setOnClickListener {
            val contact = Contact(name.text.toString(), no.text.toString())
            val contacts = mutableListOf(contact)
            contactAdapter.setUpContacts(contacts)
            alertDialog.dismiss()
        }


        binding.fab.setOnClickListener {
            alertDialog.show()
        }

    }

}