package com.prueba.firstappclean.presenter

import com.prueba.firstappclean.error.ErrorHandler
import com.prueba.firstappclean.models.SiteView

class SitesPresenter(view: View, errorHandler: ErrorHandler) : Presenter<SitesPresenter.View>(errorHandler = errorHandler, view = view) {

    override fun initialize() {
        //Traer aqui la informacion
        //Si va bien pasarlo a la vista
        view.showSites(listOf(
                SiteView("1", "1", "2,2"),
                SiteView("2", "2", "4,2"),
                SiteView("3", "3", "1,2"),
                SiteView("4", "4", "5,2")
        ))
    }

    override fun resume() {

    }

    override fun stop() {

    }

    override fun destroy() {

    }

    fun onSiteClicked(site: SiteView) {
        view.navigateToDetail(site.id)
    }

    interface View : Presenter.View {
        fun navigateToDetail(id: String)

        fun showSites(sites: List<SiteView>)
    }

}