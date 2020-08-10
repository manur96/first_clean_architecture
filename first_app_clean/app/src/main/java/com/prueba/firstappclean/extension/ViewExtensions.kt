package com.prueba.firstappclean.extension

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.prueba.domain.constants.Constants.Companion.NULL_STRING

/**
 * ViewExtensions.
 */
fun ImageView.load(url: String) {
    Glide.with(this)
            .load(url)
            .into(this)
}

/**
 * View
 */
fun View.hideMe(gone: Boolean = true) {
    this.visibility = if (gone) View.GONE else View.INVISIBLE
}

fun View.showMe() {
    this.visibility = View.VISIBLE
}

/**
 * TextViewExtensions
 */
fun TextView.changeNull() {
    if (this.text == NULL_STRING) this.text = "-"
}
