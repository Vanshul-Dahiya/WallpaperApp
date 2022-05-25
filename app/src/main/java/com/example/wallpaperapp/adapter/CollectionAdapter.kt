package com.example.wallpaperapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wallpaperapp.FinalWallpaper
import com.example.wallpaperapp.Model.bomModel
import com.example.wallpaperapp.R
import kotlinx.android.synthetic.main.item_best_of_month.view.*
import kotlinx.android.synthetic.main.item_category.view.*
import kotlinx.android.synthetic.main.item_wallppr.view.*

class CollectionAdapter(val requireContext: Context, val listBestOfMonth: ArrayList<String>)
    :RecyclerView.Adapter<CollectionAdapter.bomViewHolder>() {

    inner class bomViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionAdapter.bomViewHolder {

        return bomViewHolder(
            LayoutInflater.from(requireContext).inflate(R.layout.item_wallppr, parent, false)
        )
    }

    override fun onBindViewHolder(holder: bomViewHolder, position: Int) {
//        loads image
        Glide.with(requireContext).load(listBestOfMonth[position]).into(holder.itemView.categoryImage)

    }

    override fun getItemCount(): Int {
        return listBestOfMonth.size
    }
}