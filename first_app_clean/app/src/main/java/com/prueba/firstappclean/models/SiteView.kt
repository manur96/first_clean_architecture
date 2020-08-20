package com.prueba.firstappclean.models

data class SiteView (
        val id: String,
        val title: String,
        val geocoordinates: String,
        var fav: Boolean = false
)