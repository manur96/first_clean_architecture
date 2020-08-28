package com.prueba.firstappclean.presenter

import com.prueba.domain.interactor.usecases.GetSitesUseCase
import com.prueba.firstappclean.error.ErrorHandler
import com.prueba.firstappclean.mappers.toSiteView
import com.prueba.firstappclean.models.SiteView

class SitesListPresenter(
        private val getSitesUseCase: GetSitesUseCase,
        view: View,
        errorHandler: ErrorHandler) : Presenter<SitesListPresenter.View>(errorHandler = errorHandler, view = view) {

    var filter = false

    override fun initialize() {

    }

    override fun resume() {
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

    override fun stop() {

    }

    override fun destroy() {
        getSitesUseCase.clear()
    }

    fun onSiteClicked(site: SiteView) {
        view.navigateToDetail(site.id)
    }

    fun onFavClicked(): Boolean {
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
        return filter
    }

    interface View : Presenter.View {
        fun navigateToDetail(id: String)

        fun showSites(sites: List<SiteView>)

        fun showErrorDialog()
    }

}