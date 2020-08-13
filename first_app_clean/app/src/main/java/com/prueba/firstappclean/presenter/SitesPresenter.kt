package com.prueba.firstappclean.presenter

import com.prueba.domain.interactor.usecases.GetSitesUseCase
import com.prueba.firstappclean.error.ErrorHandler
import com.prueba.firstappclean.mappers.toSiteView
import com.prueba.firstappclean.models.SiteView

class SitesPresenter(private val getSitesUseCase: GetSitesUseCase,
                     view: View,
                     errorHandler: ErrorHandler) : Presenter<SitesPresenter.View>(errorHandler = errorHandler, view = view) {

    override fun initialize() {
        getSitesUseCase.execute(
                onSuccess = {
                    view.showSites(it.map { it.toSiteView() })
                },
                onError = {

                }
        )
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