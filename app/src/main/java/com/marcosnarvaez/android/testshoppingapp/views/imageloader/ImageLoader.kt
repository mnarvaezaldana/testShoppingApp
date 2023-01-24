package com.marcosnarvaez.android.testshoppingapp.views.imageloader

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import javax.inject.Inject

class ImageLoader @Inject constructor(private val activity: AppCompatActivity) {


    fun loadImage(imageUrl: String, target: ImageView) {
        Glide.with(activity).load(imageUrl).into(target)
    }
}