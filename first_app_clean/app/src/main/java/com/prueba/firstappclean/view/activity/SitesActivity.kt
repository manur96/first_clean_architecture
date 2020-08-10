package com.prueba.firstappclean.view.activity

import android.content.Intent
import android.view.View
import com.prueba.firstappclean.R
import com.prueba.firstappclean.presenter.SitesPresenter
import com.prueba.firstappclean.view.adapter.SitesAdapter
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class SitesActivity : RootActivity<SitesPresenter.View>(), SitesPresenter.View {
    override val progress: View
        get() = TODO("Not yet implemented")

    override val presenter: SitesPresenter by instance<SitesPresenter>()

    override val layoutResourceId: Int = R.layout.activity_sites

    override val activityModule: Kodein.Module = Kodein.Module {
        bind<SitesPresenter>() with provider {
            SitesPresenter(
                    view = this@SitesActivity,
                    errorHandler = instance()
            )
        }
    }

    private val adapter = SitesAdapter(
            onDetailClick = {
                presenter.onSiteClicked(it)
            }
    )

    override fun initializeUI() {
        //nothing to do
    }

    override fun registerListeners() {
        //nothing to do
    }

    override fun navigateToDetail(id: String) {
        val intent = Intent(this, SiteDetailActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }


}