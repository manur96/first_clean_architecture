package com.prueba.firstappclean.presenter

import com.prueba.firstappclean.error.ErrorHandler

class SitesPresenter (view:View, errorHandler: ErrorHandler) : Presenter<SitesPresenter.View>(errorHandler = errorHandler, view = view) {

    override fun initialize() {
        //Traer aqui la informacion
        //Si va bien pasarlo a la vista
        view.showMessage("Hola")
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