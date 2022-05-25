package com.example.wallpaperapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wallpaperapp.Model.CategoryModel
import com.example.wallpaperapp.Model.ColorToneModel
import com.example.wallpaperapp.Model.bomModel
import com.example.wallpaperapp.R
import com.example.wallpaperapp.adapter.CategoryAdapter
import com.example.wallpaperapp.adapter.ColorToneAdapter
import com.example.wallpaperapp.adapter.bomAdapter
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    lateinit var db : FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        db = FirebaseFirestore.getInstance()

        db.collection("bestofmonth").addSnapshotListener { value, error ->

            val listBestOfMonth = arrayListOf<bomModel>()

//            store the value we got from firebase
            val data = value?.toObjects(bomModel::class.java)
//            add fetched data in list -> to display
            listBestOfMonth.addAll(data!!)

            rcvBom.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            rcvBom.adapter = bomAdapter(requireContext(),listBestOfMonth)
        }


        db.collection("colortone").addSnapshotListener { value, error ->

            val listColorTone = arrayListOf<ColorToneModel>()

            val data = value?.toObjects(ColorToneModel::class.java)

            listColorTone.addAll(data!!)

            rcvColorTone.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            rcvColorTone.adapter = ColorToneAdapter(requireContext(),listColorTone)
        }

        db.collection("categories").addSnapshotListener { value, error ->

            val listOfCategory = arrayListOf<CategoryModel>()

            val data = value?.toObjects(CategoryModel::class.java)

            listOfCategory.addAll(data!!)

            rcvCategory.layoutManager = GridLayoutManager(requireContext(),2)
            rcvCategory.adapter = CategoryAdapter(requireContext(),listOfCategory)
        }


        return inflater.inflate(R.layout.fragment_home, container, false)
    }

}