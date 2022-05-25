package com.example.wallpaperapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.wallpaperapp.Model.CategoryModel
import com.example.wallpaperapp.Model.bomModel
import com.example.wallpaperapp.adapter.CatImagesAdapter
import com.example.wallpaperapp.adapter.CategoryAdapter
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.fragment_home.*

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val db = FirebaseFirestore.getInstance()

        val uid = intent.getStringExtra("uid")
        val name = intent.getStringExtra("name")


        db.collection("categories").document(uid!!).collection("wallpaper").addSnapshotListener { value, error ->

            val listOfCatWllpr = arrayListOf<bomModel>()

            val data = value?.toObjects(bomModel::class.java)

            listOfCatWllpr.addAll(data!!)

            CategoryTitle.text = name.toString()
            CategoryCount.text = "${listOfCatWllpr.size} wallpaper available"

            RCVCAT.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            RCVCAT.adapter = CatImagesAdapter(this,listOfCatWllpr)

        }

    }
}