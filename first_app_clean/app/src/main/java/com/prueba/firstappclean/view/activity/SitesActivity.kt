package com.prueba.firstappclean.view.activity

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.prueba.firstappclean.R
import com.prueba.firstappclean.presenter.SitesPresenter
import com.prueba.firstappclean.view.adapter.ViewPagerAdapter
import com.prueba.firstappclean.view.fragment.MapsFragment
import com.prueba.firstappclean.view.fragment.SitesFragment
import kotlinx.android.synthetic.main.activity_sites.*
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
        val pagerAdapter = ViewPagerAdapter(supportFragmentManager)

        pagerAdapter.addFragment("Sites", SitesFragment.newInstance())
        pagerAdapter.addFragment("Map", MapsFragment())
        pager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(pager)
    }

    override fun registerListeners() {
        //nothing to do
    }

}