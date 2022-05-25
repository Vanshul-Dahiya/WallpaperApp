package com.example.wallpaperapp.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wallpaperapp.CategoryActivity
import com.example.wallpaperapp.Model.CategoryModel
import com.example.wallpaperapp.Model.ColorToneModel
import com.example.wallpaperapp.R
import kotlinx.android.synthetic.main.item_category.view.*
import kotlinx.android.synthetic.main.item_color_tone.view.*


class CategoryAdapter(val requireContext: Context, val listOfCategory: ArrayList<CategoryModel>)
    :RecyclerView.Adapter<CategoryAdapter.bomViewHolder>() {

    inner class bomViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.bomViewHolder {

        return bomViewHolder(
            LayoutInflater.from(requireContext).inflate(R.layout.item_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: bomViewHolder, position: Int) {

//        setting name of categroy
        holder.itemView.categroyName.text = listOfCategory[position].name

//        setting image
        Glide.with(requireContext).load(listOfCategory[position].link).into(holder.itemView.categoryImage)

        holder.itemView.setOnClickListener {
            val intent = Intent(requireContext,CategoryActivity::class.java)
            intent.putExtra("uid",listOfCategory[position].id)
            intent.putExtra("name",listOfCategory[position].name)
            requireContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listOfCategory.size
    }
}