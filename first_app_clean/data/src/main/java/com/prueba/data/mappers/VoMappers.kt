package com.prueba.data.mappers

import com.prueba.data.model.LocationVo
import com.prueba.data.model.SiteDetailVo
import com.prueba.data.model.SiteVo
import com.prueba.domain.models.Location
import com.prueba.domain.models.Site
import com.prueba.domain.models.SiteDetail

fun Site.toVo(): SiteVo = SiteVo(
        id = id,
        title = title,
        hasDetail = hasDetail
)

fun SiteVo.toModel(): Site = Site(
        id = id,
        title = title,
        location = Location(0.0, 0.0), //Fixme
        hasDetail = hasDetail
)

fun SiteDetail.toVo(): SiteDetailVo = SiteDetailVo(
        id = id,
        title = title,
        address = address,
        description = description,
        email = email,
        geocoordinates = "0,0",
        phone = phone,
        transport = transport,
        isFav = isFav
)

fun SiteDetailVo.toModel(): SiteDetail = SiteDetail(
        id = id,
        title = title,
        address = address,
        description = description,
        email = email,
        location = Location(0.0, 0.0),
        phone = phone,
        transport = transport,
        isFav = isFav
)

fun Location.toVo(): LocationVo = LocationVo(
        lat = lat,
        lng = lng
)

fun LocationVo.toModel(): Location = Location(
        lat = lat,
        lng = lng
)
