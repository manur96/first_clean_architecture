package com.prueba.data.mappers

import com.prueba.data.model.LocationVo
import com.prueba.data.model.SiteVo
import com.prueba.domain.models.Location
import com.prueba.domain.models.Site

fun Site.toVo(): SiteVo = SiteVo(
        id = id,
        title = title,
        fav = fav
)

fun SiteVo.toModel(): Site = Site(
        id = id,
        title = title,
        location = Location(0.0, 0.0), //Fixme
        fav = fav
)

fun Location.toVo(): LocationVo = LocationVo(
        lat = lat,
        lng = lng
)

fun LocationVo.toModel(): Location = Location(
        lat = lat,
        lng = lng
)
