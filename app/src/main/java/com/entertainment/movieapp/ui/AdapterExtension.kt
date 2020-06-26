package com.entertainment.movieapp.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.entertainment.movieapp.R

@BindingAdapter("android:srcImage")
fun setImageViewResource(imageView: ImageView, imageUrl: String) {
    if(imageUrl == "")
    {
        Glide.with(imageView.context)
            .load(R.drawable.ic_placeholder)
            .into(imageView)

    }else{
        Glide.with(imageView.context)
            .load(imageUrl).apply(RequestOptions().fitCenter())
            .placeholder(R.drawable.ic_placeholder)
            .into(imageView)
    }

}