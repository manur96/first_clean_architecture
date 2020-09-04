package com.prueba.data.mappers

import com.prueba.data.model.SiteDetailResponseDto
import com.prueba.data.model.SiteDto
import com.prueba.domain.models.Location
import com.prueba.domain.models.Site
import com.prueba.domain.models.SiteDetail

fun SiteDto.toModel(): Site {
    val array = geocoordinates.split(',')
    val location = Location(array[0].toDouble(), array[1].toDouble())
    return Site(
            id = id,
            title = title,
            location = location
    )
}

fun SiteDetailResponseDto.toModel(): SiteDetail {
    val array = geocoordinates.split(',')
    val location = Location(array[0].toDouble(), array[1].toDouble())
    return SiteDetail(
            id = id,
            title = title,
            location = location,
            address = address,
            description = description,
            email = email,
            phone = phone,
            transport = transport
    )
}