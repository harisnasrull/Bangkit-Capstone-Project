package com.nasuru.pari

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.nasuru.pari.databinding.ActivityMainBinding
import com.nasuru.pari.helper.BitmapHelper

private const val REQUEST_CODE = 77

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        BitmapHelper.instance.bitmap = null

        binding.camera.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if (takePictureIntent.resolveActivity(this.packageManager) != null) {
                startActivityForResult(takePictureIntent, REQUEST_CODE)
            } else {
                Toast.makeText(this, "Unable to Open Camera", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val image = data?.extras?.get("data") as Bitmap
            BitmapHelper.instance.bitmap = image
            val intent = Intent(this,DetailActivity::class.java)
            startActivity(intent)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}