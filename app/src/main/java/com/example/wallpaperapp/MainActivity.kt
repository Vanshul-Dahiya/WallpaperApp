package com.example.wallpaperapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.wallpaperapp.Fragments.DownloadFragment
import com.example.wallpaperapp.Fragments.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        replaceFragment(HomeFragment())

        ic_home.setOnClickListener{
            replaceFragment(HomeFragment())
            Toast.makeText(this, "Home Clicked", Toast.LENGTH_SHORT).show()
        }
        ic_download.setOnClickListener{
            replaceFragment(DownloadFragment())
            Toast.makeText(this, "Download Clicked", Toast.LENGTH_SHORT).show()
        }

    }
    fun replaceFragment( fragemnt : Fragment){
       val transaction = supportFragmentManager.beginTransaction()
       transaction.replace(R.id.FragReplace, fragemnt)
       transaction.commit()
    }
}