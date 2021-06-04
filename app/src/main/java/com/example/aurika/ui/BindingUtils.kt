package com.example.aurika.ui

import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.aurika.R

@BindingAdapter("bookImage")
fun setImage(imageView: ImageView, item: BookDomain) {

    val circularProgressDrawable = CircularProgressDrawable(imageView.context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

        Glide.with(imageView.context)
            .load(item.imgSrc)
            .error(R.drawable.ic_baseline_image_not_supported_24)
            .transform(RoundedCorners(12))
            .placeholder(circularProgressDrawable)
            .into(imageView)


}

@BindingAdapter("bookTitle")
fun TextView.setTitle(item: BookDomain) {

    text = item.name

}

@BindingAdapter("bookDescription")
fun TextView.setDescription(item: BookDomain) {

    text = if (item.description == "ERROR100") " " else item.description

}

@BindingAdapter("bookRating")
fun RatingBar.setRating(item: BookDomain) {

    rating = item.rating.toFloat()
}

@BindingAdapter("bookAuthors")
fun TextView.setAuthors(item: BookDomain) {

    text = item.authors?.joinToString(", ") ?: " "

}