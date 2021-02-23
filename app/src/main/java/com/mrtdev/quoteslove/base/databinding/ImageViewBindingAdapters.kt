package com.mrtdev.quoteslove.base.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:src")
fun bindImage(view: ImageView, image: Int?) {
    image?.let(view::setImageResource) ?: view.setImageDrawable(null)
}