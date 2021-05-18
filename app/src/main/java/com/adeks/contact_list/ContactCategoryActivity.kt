package com.adeks.contact_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adeks.contact_list.databinding.ActivityContactCategoryBinding

class ContactCategoryActivity : AppCompatActivity() {
    lateinit var binding: ActivityContactCategoryBinding
    lateinit var categoryAdapter: CategoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        categoryAdapter = CategoryAdapter(this)
        setUpData(binding)
    }

    private fun setUpData(binding: ActivityContactCategoryBinding) {
        val builder = AlertDialog.Builder(this)
        binding.categoryList.layoutManager = GridLayoutManager(this, 2)
        binding.categoryList.addItemDecoration(DividerItemDecoration(this,GridLayout.VERTICAL))
        val view = layoutInflater.inflate(R.layout.add_category_dialog, null)
        builder.setView(view)
        val saveBtn = view.findViewById<Button>(R.id.save_cat)
        val cat = view.findViewById<TextView>(R.id.categoryEt)
        val alertDialog = builder.create()
        saveBtn.setOnClickListener {
            if (cat.text.toString() != "") {
                val category = Category(cat.text.toString())
                val categories = mutableListOf(category)
                categoryAdapter.setUpCategoryList(categories)
                alertDialog.dismiss()
            }
        }

        binding.categoryList.adapter = categoryAdapter
        binding.addCategoryfab.setOnClickListener {
            alertDialog.show()
        }

    }
}