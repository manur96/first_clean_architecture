package com.prueba.firstappclean.presenter

import com.prueba.domain.interactor.usecases.GetSitesUseCase
import com.prueba.firstappclean.Subject
import com.prueba.firstappclean.error.ErrorHandler
import com.prueba.firstappclean.mappers.toSiteView
import com.prueba.firstappclean.models.SiteView
import io.reactivex.disposables.Disposable

class SitesListPresenter(
        view: View,
        errorHandler: ErrorHandler) : Presenter<SitesListPresenter.View>(errorHandler = errorHandler, view = view) {

    private lateinit var filtersSubscription: Disposable

    override fun initialize() {
        filtersSubscription = Subject.listSitesSubject.subscribe {
            view.showSites(it.map { it.toSiteView() })
        }
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

    fun onSiteClicked(site: SiteView) {
        view.navigateToDetail(site.id)
    }

    interface View : Presenter.View {
        fun navigateToDetail(id: String)
        fun showSites(sites: List<SiteView>)
    }

}