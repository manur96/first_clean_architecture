package com.prueba.data.model

import com.google.gson.annotations.SerializedName


data class SitesResponseDto(
        @SerializedName("list") val sites: List<SiteDto>
)

data class SiteDto(
        @SerializedName("geocoordinates") val geocoordinates: String,
        @SerializedName("id") val id: String,
        @SerializedName("title") val title: String
)