package com.mrtdev.quoteslove.base.databinding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("android:visibility")
fun bindVisibility(view: View, isVisible: Boolean?) {
    view.visibility = when (isVisible) {
        true -> View.VISIBLE
        else -> View.GONE
    }
}