package com.adeks.contact_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adeks.contact_list.databinding.ContactListItemBinding

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    private val contact  = mutableListOf<Contact>()

    fun setUpContacts(contacts: List<Contact>) {
        this.contact.addAll(contacts)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            ContactListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contact[position]
        holder.bindItem(contact)
    }

    override fun getItemCount(): Int {
        return contact.size
    }

    inner class ContactViewHolder(private val binding: ContactListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(contact: Contact) {
            binding.nameTv.text = contact.name
            binding.numberTv.text = contact.number
        }
    }
}