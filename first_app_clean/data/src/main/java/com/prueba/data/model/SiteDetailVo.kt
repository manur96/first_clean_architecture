package com.prueba.data.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class SiteDetailVo(
        @PrimaryKey
        var id: String = "",
        var address: String = "",
        var description: String = "",
        var email: String = "",
        var geocoordinates: String = "",
        var phone: String = "",
        var title: String = "",
        var transport: String = "",
        var isFav: Boolean = false
) : RealmObject() {
    companion object {
        const val PRIMARY_KEY = "id"
    }
}