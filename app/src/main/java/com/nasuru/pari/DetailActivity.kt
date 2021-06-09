package com.nasuru.pari

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.nasuru.pari.databinding.ActivityDetailBinding
import com.nasuru.pari.helper.BitmapHelper
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bitmap = BitmapHelper.instance.bitmap

        if (bitmap != null) {
            val uri = bitmapToFile(bitmap)
            binding.imageCamera.setImageURI(uri)

//            binding.category.text = uri.toString()
        } else {
            Toast.makeText(applicationContext,"bitmap not found.",Toast.LENGTH_SHORT).show()
        }

        binding.buttonBack.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        binding.license.setOnClickListener {
            startActivity(Intent(this, OssLicensesMenuActivity::class.java))
        }
    }

    private fun bitmapToFile(bitmap: Bitmap): Uri {

        // Get the context wrapper
        val wrapper = ContextWrapper(applicationContext)

        // Initialize a new file instance to save bitmap object
        var file = wrapper.getDir("Images", Context.MODE_PRIVATE)
        file = File(file, "images.jpg")

        try {
            // Compress the bitmap and save in jpg format
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        // Return the saved bitmap uri
        return Uri.parse(file.absolutePath)
    }
}