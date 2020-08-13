package com.prueba.data.mappers

import com.prueba.data.model.SiteDto
import com.prueba.domain.models.Site

fun SiteDto.toModel(): Site = Site(
        id = id,
        title = title,
        geocoordinates = geocoordinates
)