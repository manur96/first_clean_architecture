package com.prueba.domain.models

data class Site (
        val id: String,
        val title: String,
        val geocoordinates: String,
        var fav: Boolean = false
)