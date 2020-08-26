package com.prueba.data.persistence

import io.reactivex.Completable

interface Settings {
    fun addToFavorite(idSite: String): Completable
    fun hasFavorites(): Boolean
    fun getFavorites(): List<String>
}