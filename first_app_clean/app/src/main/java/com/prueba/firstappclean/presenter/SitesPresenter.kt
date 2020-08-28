package com.prueba.firstappclean.presenter

import com.prueba.firstappclean.error.ErrorHandler

class SitesPresenter(view: SitesPresenter.View, errorHandler: ErrorHandler) : Presenter<SitesPresenter.View>(errorHandler = errorHandler, view = view) {

    override fun initialize() {

    }

    override fun resume() {

    }

    override fun stop() {

    }

    override fun destroy() {

    }

    interface View : Presenter.View {

    }


}