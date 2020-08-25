package com.prueba.domain.models

data class SiteDetail(
        val address: String,
        val description: String,
        val email: String,
        val geocoordinates: String,
        val id: String,
        val phone: String,
        val title: String,
        val transport: String,
        var isFav: Boolean = false
)