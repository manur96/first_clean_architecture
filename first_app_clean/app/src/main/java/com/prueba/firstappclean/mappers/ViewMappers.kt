package com.prueba.firstappclean.mappers

import com.prueba.domain.models.Site
import com.prueba.domain.models.SiteDetail
import com.prueba.firstappclean.models.SiteDetailView
import com.prueba.firstappclean.models.SiteView

fun Site.toSiteView(): SiteView = SiteView(
        id = id,
        title = title,
        geocoordinates = geocoordinates,
        fav = fav
)

fun SiteDetail.toSiteDetailView(): SiteDetailView = SiteDetailView(
        id = id,
        title = title,
        address = address,
        description = description,
        email = email,
        geocoordinates = geocoordinates,
        phone = phone,
        transport = transport
)
