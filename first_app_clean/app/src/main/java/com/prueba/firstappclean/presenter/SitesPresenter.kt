package com.prueba.firstappclean.presenter

import com.prueba.domain.interactor.usecases.GetSitesUseCase
import com.prueba.firstappclean.Subject
import com.prueba.firstappclean.error.ErrorHandler
import com.prueba.firstappclean.mappers.toSiteView
import com.prueba.firstappclean.models.SiteView

class SitesPresenter(
        private val getSitesUseCase: GetSitesUseCase,
        view: View,
        errorHandler: ErrorHandler) : Presenter<SitesPresenter.View>(errorHandler = errorHandler, view = view) {

    private var filter = false

    override fun initialize() {
        //nothing to do
    }

    override fun resume() {
        view.showProgress()
        getSitesUseCase.execute(
                filter = filter,
                onSuccess = {
                    Subject.listSitesSubject.onNext(it)
                    view.hideProgress()
                },
                onError = {
                    view.hideProgress()
                    view.showError("Error")
                }
        )
    }

    override fun stop() {
        //nothing to do
    }

    override fun destroy() {
        getSitesUseCase.clear()
    }

    fun onFavClicked(): Boolean {
        filter = !filter
        getSitesUseCase.execute(
                filter = filter,
                onSuccess = {
                    Subject.listSitesSubject.onNext(it)
                },
                onError = {
                    view.showError("Error")
                }
        )
        return filter
    }

    interface View : Presenter.View {
        //nothing to do
    }


}