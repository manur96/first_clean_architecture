package com.prueba.domain.models

data class SiteDetail(
        val address: String,
        val description: String,
        val email: String,
        val location: Location,
        val id: String,
        val phone: String,
        val title: String,
        val transport: String,
        var isFav: Boolean = false
)