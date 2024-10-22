package com.example.retrofitresimcekme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CustomAdapter(private val userList: List<Images>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvId: TextView = itemView.findViewById(R.id.tvId)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // item_layout.xml'i şişiriyoruz
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]

        // Verileri atama
        holder.tvId.text = "ID: ${user.id}"
        holder.tvTitle.text = "Title: ${user.title}"

        // Picasso ile resim yükleme
        Picasso.get()
            .load(user.url)  // URL resim URL'si olmalı
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}