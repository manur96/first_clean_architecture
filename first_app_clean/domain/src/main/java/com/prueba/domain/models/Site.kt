package com.prueba.domain.models

data class Site (
        val id: String,
        val title: String,
        val location: Location,
        var fav: Boolean = false,
        var hasDetail: Boolean = false
)