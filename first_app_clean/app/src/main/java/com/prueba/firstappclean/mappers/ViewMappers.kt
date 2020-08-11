package com.prueba.firstappclean.mappers

import com.prueba.domain.models.Site
import com.prueba.firstappclean.models.SiteView

fun Site.toSiteView(): SiteView = SiteView(
        id = id,
        title = title,
        geocoordinates = geocoordinates
)
