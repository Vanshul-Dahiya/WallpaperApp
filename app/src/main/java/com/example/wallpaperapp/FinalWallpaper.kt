package com.example.wallpaperapp

import android.app.WallpaperManager
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_final_wallpaper.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException
import java.io.OutputStream
import java.net.URL
import java.util.*

class FinalWallpaper : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_final_wallpaper)

        supportActionBar?.hide()

        val url = intent.getStringExtra("link")

//       convert url from string to URL format
        val urlImage = URL(url)

//        set wallpaper of above passed link
        Glide.with(this).load(url).into(finalWallpaper)

        btnDownloadWallppr.setOnClickListener {

            val result : kotlinx.coroutines.Deferred<Bitmap?> = GlobalScope.async {
                urlImage.toBitmap()
            }

            GlobalScope.launch(Dispatchers.Main) {
//                save image
                saveImage(result.await())
            }
        }
        btnSetWallppr.setOnClickListener {

            val result : kotlinx.coroutines.Deferred<Bitmap?> = GlobalScope.async {
                urlImage.toBitmap()
            }

            GlobalScope.launch(Dispatchers.Main) {
                val wlprManager = WallpaperManager.getInstance(applicationContext)
                wlprManager.setBitmap(result.await())
            }


        }
    }
    fun URL.toBitmap() : Bitmap?{
        return try {
            BitmapFactory.decodeStream(openStream())
        }catch (e : IOException){
            null
        }
    }
    private fun saveImage(image : Bitmap?){
        val random1  = Random().nextInt(520985)
        val random2  = Random().nextInt(952663)

//        name of wallpaper -> ex -  Amoled1221
        val name = "AMOLED-${random1 + random2}"

        val data : OutputStream
        try {
            val resolver = contentResolver

//            Define type and name of image using MediaStore
//            then give path of image where it is to be stored
//            then compress and save

            val contentValues = ContentValues()
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME,"$name.jpg")
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE,"image/jpg")
            contentValues.put(
                MediaStore.MediaColumns.RELATIVE_PATH,
                Environment.DIRECTORY_PICTURES + File.separator + "Amoled Wallpaper"
            )
            val imageUri =
                resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues)
            data = resolver.openOutputStream(Objects.requireNonNull(imageUri!!))!!
            image?.compress(Bitmap.CompressFormat.JPEG,100,data)
            Objects.requireNonNull<OutputStream?>(data)
            Toast.makeText(this, "Image saved", Toast.LENGTH_SHORT).show()

        }catch (e : Exception){
            Toast.makeText(this, e.message , Toast.LENGTH_SHORT).show()
        }
    }
}