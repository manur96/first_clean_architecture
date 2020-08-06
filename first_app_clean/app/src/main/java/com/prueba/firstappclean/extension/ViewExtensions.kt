package com.prueba.firstappclean.extension

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.w3c.dom.Text

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
 * */
fun View.hideMe(gone: Boolean = true) {
    this.visibility = if (gone) View.GONE else View.INVISIBLE
}

fun View.showMe() {
    this.visibility = View.VISIBLE
}

/**
 * TextView
 */
fun TextView.changeNull(){
    if (this.text == "null") this.text = "-"
}
