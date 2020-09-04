package com.prueba.firstappclean.models


data class SiteDetailView(
        val address: String,
        val description: String,
        val email: String,
        val geocoordinates: String,
        val id: String,
        val phone: String,
        val title: String,
        val transport: String,
        val isFav: Boolean
)