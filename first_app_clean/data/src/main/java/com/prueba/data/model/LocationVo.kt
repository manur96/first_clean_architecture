package com.prueba.data.model

import io.realm.RealmObject

open class LocationVo(
        var lat: Double = 0.0,
        var lng: Double = 0.0
) : RealmObject()