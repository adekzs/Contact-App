package com.adeks.contact_list

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adeks.contact_list.databinding.CategoryListItemBinding
import java.util.*
import kotlin.coroutines.coroutineContext

class CategoryAdapter(context : Context) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var context : Context = context
    private var categories = mutableListOf<Category>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(CategoryListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

     fun setUpCategoryList(categories : List<Category>) {
        this.categories.addAll(categories)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindItem(categories[position])
        holder.itemView.setOnClickListener {
            val intent = Intent( context, MainActivity::class.java)
            intent.putExtra(categoryName, categories[position].categoryName)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    inner class CategoryViewHolder(private val binding : CategoryListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(cat : Category) {
            binding.categoryTv.text = cat.categoryName
            binding.firstLetter.text = cat.categoryName.substring(0, 1).toUpperCase(Locale.ROOT)
        }
    }

    companion object {
        var categoryName : String = "category"
    }
}