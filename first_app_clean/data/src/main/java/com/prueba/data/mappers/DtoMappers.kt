package com.prueba.data.mappers

import com.prueba.data.model.SiteDetailResponseDto
import com.prueba.data.model.SiteDto
import com.prueba.domain.models.Site
import com.prueba.domain.models.SiteDetail

fun SiteDto.toModel(): Site = Site(
        id = id,
        title = title,
        geocoordinates = geocoordinates
)

fun SiteDetailResponseDto.toModel(): SiteDetail {
    return SiteDetail(
            id = id,
            title = title,
            geocoordinates = geocoordinates,
            address = address,
            description = description,
            email = email,
            phone = phone,
            transport = transport
    )
}