package com.example.wallpaperapp.Fragments

import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.wallpaperapp.R
import com.example.wallpaperapp.adapter.CollectionAdapter
import kotlinx.android.synthetic.main.fragment_download.*
import java.io.File


class DownloadFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

//        make an array of type file , in which we will get all files
//        then we'll put those file in diff list

        val allFiles : Array<File>
        val imageList = arrayListOf<String>()

//        make target path i.e in which folder we want our data

        val targetPath = Environment.getExternalStorageDirectory().absolutePath+"/Pictures/Amoled Wallpaper"

        val targetFile = File(targetPath)

//        stored files
        allFiles=targetFile.listFiles()!!

        for (data in allFiles){
            imageList.add(data.absolutePath)
        }

        rcvDownload.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        rcvDownload.adapter = CollectionAdapter(requireContext(),imageList)

        return inflater.inflate(R.layout.fragment_download, container, false)
    }
}