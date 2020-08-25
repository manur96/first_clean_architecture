package com.prueba.firstappclean.presenter

import com.prueba.domain.interactor.usecases.GetSitesUseCase
import com.prueba.firstappclean.error.ErrorHandler
import com.prueba.firstappclean.mappers.toSiteView
import com.prueba.firstappclean.models.SiteView

class SitesPresenter(
        private val getSitesUseCase: GetSitesUseCase,
        view: View,
        errorHandler: ErrorHandler) : Presenter<SitesPresenter.View>(errorHandler = errorHandler, view = view) {

    var filter = false

    override fun initialize() {
        view.showProgress()
        getSitesUseCase.execute(
                filter = filter,
                onSuccess = {
                    view.showSites(it.map { it.toSiteView() })
                    view.hideProgress()
                },
                onError = {
                    view.hideProgress()
                    view.showErrorDialog()
                }
        )
    }

    override fun resume() {

    }

    override fun stop() {

    }

    override fun destroy() {
        getSitesUseCase.clear()
    }

    fun onSiteClicked(site: SiteView) {
        view.navigateToDetail(site.id)
    }

    fun onFavClicked() {
        filter = !filter
        getSitesUseCase.execute(
                filter = filter,
                onSuccess = {
                    view.showSites(it.map { it.toSiteView() })
                },
                onError = {
                    view.showErrorDialog()
                }
        )
    }

    interface View : Presenter.View {
        fun navigateToDetail(id: String)

        fun showSites(sites: List<SiteView>)
    }

}