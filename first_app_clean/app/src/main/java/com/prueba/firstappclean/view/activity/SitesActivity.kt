package com.prueba.firstappclean.view.activity

import android.view.View
import com.prueba.firstappclean.R
import com.prueba.firstappclean.presenter.SitesPresenter
import com.prueba.firstappclean.view.fragment.SitesFragment
import kotlinx.android.synthetic.main.view_progress.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider


class SitesActivity : RootActivity<SitesPresenter.View>(), SitesPresenter.View {

    override val progress: View by lazy { progressView }

    override val presenter: SitesPresenter by instance<SitesPresenter>()

    override val layoutResourceId: Int = R.layout.activity_sites

    override val activityModule: Kodein.Module = Kodein.Module("App") {
        bind<SitesPresenter>() with provider {
            SitesPresenter(
                    view = this@SitesActivity,
                    errorHandler = instance()
            )
        }
    }


    override fun initializeUI() {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, SitesFragment.newInstance()).commit()
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer2, SitesFragment.newInstance()).commit()
    }

    override fun registerListeners() {

    }

}