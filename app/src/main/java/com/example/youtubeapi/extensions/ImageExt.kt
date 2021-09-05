package com.example.youtubeapi.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.load(s: String) {
    Glide.with(context).load(s).centerCrop().into(ImageView())
}

fun ImageView(): ImageView {
return ImageView()
}
