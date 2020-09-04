package com.prueba.firstappclean.presenter

import com.prueba.domain.models.Site
import com.prueba.firstappclean.Subject
import com.prueba.firstappclean.error.ErrorHandler
import io.reactivex.disposables.Disposable

class MapsPresenter(
        view: View,
        errorHandler: ErrorHandler) : Presenter<MapsPresenter.View>(errorHandler = errorHandler, view = view) {

    private lateinit var sitesSubscription: Disposable

    private var sites: List<Site> = listOf()

    override fun initialize() {
        sitesSubscription = Subject.listSitesSubject.subscribe {
            sites = it
            showSites()
        }
    }

    private fun showSites() {
        view.showSites(sites)
    }

    override fun resume() {
        //nothing to do
    }

    override fun stop() {
        //nothing to do
    }

    override fun destroy() {
        //nothing to do
    }

    fun onMapReady() {
        showSites()
    }

    interface View : Presenter.View {
        fun showSites(sites: List<Site>)
    }
}