package com.example.wallpaperapp.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaperapp.FinalWallpaper
import com.example.wallpaperapp.Model.ColorToneModel
import com.example.wallpaperapp.R
import kotlinx.android.synthetic.main.item_color_tone.view.*


class ColorToneAdapter(val requireContext: Context, val listColorTone: ArrayList<ColorToneModel>)
    :RecyclerView.Adapter<ColorToneAdapter.bomViewHolder>() {

    inner class bomViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorToneAdapter.bomViewHolder {

        return bomViewHolder(
            LayoutInflater.from(requireContext).inflate(R.layout.item_color_tone, parent, false)
        )
    }

    override fun onBindViewHolder(holder: bomViewHolder, position: Int) {
        val color = listColorTone[position].color

     holder.itemView.itemCard.setCardBackgroundColor(Color.parseColor(color!!))

        holder.itemView.setOnClickListener {
            val intent = Intent(requireContext, FinalWallpaper::class.java)
            intent.putExtra("link",listColorTone[position].link)
            requireContext.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return listColorTone.size
    }
}