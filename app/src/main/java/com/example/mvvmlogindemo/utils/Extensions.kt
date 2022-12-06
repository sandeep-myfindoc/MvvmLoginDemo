package com.example.mvvmlogindemo.utils

import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

// created extension function to use url to parse image
/*@BindingAdapter("imageFromUrl")
fun ImageView.imageFromUrl(url:String){ // here this represent to view
    Glide.with(this.context).load(url).into(this)
}*/
@BindingAdapter ("imageUrl","placeHolder")
fun ImageView.imageFromUrl(url: String,drawable: Drawable){
    Glide.with(this.context).load(url)
        .placeholder(drawable)
        .into(this)
}
