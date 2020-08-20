package com.prueba.firstappclean.models
import com.google.gson.annotations.SerializedName


data class SiteDetailView(
    @SerializedName("address")
    val address: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("geocoordinates")
    val geocoordinates: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("transport")
    val transport: String
)