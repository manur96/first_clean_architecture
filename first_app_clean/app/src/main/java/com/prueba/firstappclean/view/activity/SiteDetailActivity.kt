package com.prueba.firstappclean.view.activity

import android.view.View
import com.prueba.firstappclean.R
import com.prueba.firstappclean.models.SiteDetailView
import com.prueba.firstappclean.presenter.SiteDetailPresenter
import kotlinx.android.synthetic.main.activity_site_detail.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class SiteDetailActivity : RootActivity<SiteDetailPresenter.View>(), SiteDetailPresenter.View {
    override val progress: View
        get() = TODO("Not yet implemented")

    override val presenter: SiteDetailPresenter by instance<SiteDetailPresenter>()

    override val layoutResourceId: Int = R.layout.activity_site_detail

    override val activityModule: Kodein.Module = Kodein.Module {
        bind<SiteDetailPresenter>() with provider {
            SiteDetailPresenter(
                    getSiteDetailUseCase = instance(),
                    view = this@SiteDetailActivity,
                    errorHandler = instance()
            )
        }
    }

    override fun initializeUI() {
        //Creo que nothing to do
    }

    override fun registerListeners() {
        presenter.onDetailClick(intent.getStringExtra("id"))
    }

    override fun showDetail(siteDetail: SiteDetailView) {
        titleDetail.text = siteDetail.title
        addres.text = siteDetail.address
        transport.text = siteDetail.transport
        email.text = siteDetail.email
        geocoordinatesDetail.text = siteDetail.geocoordinates
        description.text = siteDetail.description
        phone.text = siteDetail.phone
    }


}