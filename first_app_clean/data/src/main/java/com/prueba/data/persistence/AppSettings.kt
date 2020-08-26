package com.prueba.data.persistence

import android.content.Context
import com.google.gson.Gson
import io.reactivex.Completable

class AppSettings(context: Context, name: String) : Settings {

    companion object {
        private const val ID_SITE_KEY: String = "ID_SITE_KEY"
    }

    private val sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    override fun addToFavorite(idSite: String): Completable {
        val items = if (hasFavorites()) {
            getFavorites().toMutableList()
        } else {
            mutableListOf()
        }
        items.add(idSite)
        val sitesJson: String = Gson().toJson(items)
        sharedPreferences.edit().putString(ID_SITE_KEY, sitesJson).apply()

        return Completable.complete()
    }

    override fun removeFromFavorite(idSite: String): Completable {
        //sharedPreferences.edit().remove(idSite).apply()
        val favSites = getFavorites().toMutableList()
        if (favSites.contains(idSite))
            favSites.remove(idSite)
/*        for (id in getFavorites()) {
            if (id == idSite) {
                favSites.remove(idSite)
            }
        }*/

        val sitesJson: String = Gson().toJson(favSites)
        sharedPreferences.edit().putString(ID_SITE_KEY, sitesJson).apply()

        return Completable.complete()
    }

    override fun hasFavorites(): Boolean {
        return sharedPreferences.contains(ID_SITE_KEY)
    }

    override fun getFavorites(): List<String> {
        return Gson().fromJson<List<String>>(sharedPreferences.getString(ID_SITE_KEY, "Error al recuperar preferencias"), mutableListOf<String>()::class.java)
    }
}