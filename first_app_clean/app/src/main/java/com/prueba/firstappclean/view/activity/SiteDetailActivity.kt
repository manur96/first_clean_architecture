package com.prueba.firstappclean.view.activity

import android.preference.PreferenceManager
import android.view.View
import android.widget.Toast
import com.prueba.firstappclean.R
import com.prueba.firstappclean.models.SiteDetailView
import com.prueba.firstappclean.models.SiteView
import com.prueba.firstappclean.presenter.SiteDetailPresenter
import com.prueba.firstappclean.view.adapter.SitesAdapter
import kotlinx.android.synthetic.main.activity_site_detail.*
import kotlinx.android.synthetic.main.view_progress.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class SiteDetailActivity : RootActivity<SiteDetailPresenter.View>(), SiteDetailPresenter.View {

    override val progress: View by lazy { progressView }

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

    private val adapter = SitesAdapter {
        presenter.onFavClicked(it)
    }

    override fun initializeUI() {
        //nothing to do
    }

    override fun registerListeners() {
        //nothing to do
    }

    override fun getId(): String {
        return intent.getStringExtra("id")
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

    override fun addToFavorites(site: SiteView) {
        site.fav = true

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        sharedPreferences.edit().putString("ID_SITE_KEY", site.id).apply()
        sharedPreferences.edit().putString("TITLE_SITE_KEY", site.title).apply()
        sharedPreferences.edit().putString("GEO_SITE_KEY", site.geocoordinates).apply()

        Toast.makeText(this, "Sitio guardado en favoritos", Toast.LENGTH_SHORT)
    }

}