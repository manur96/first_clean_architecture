package com.prueba.firstappclean.view.activity

import android.app.Activity
import android.os.Bundle
import com.prueba.data.datasource.network.SiteService
import com.prueba.firstappclean.R
import com.prueba.firstappclean.extension.changeNull
import kotlinx.android.synthetic.main.activity_site_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PreviousSiteDetailActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_detail)

        //getSiteById()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("http://t21services.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    /*private fun getSiteById() {
        val service = getRetrofit().create(SiteService::class.java)
        val id = intent.getStringExtra("id")
        if (id != null) {
            service.getPointById(id).enqueue(object : Callback<SiteDetail> {
                override fun onResponse(call: Call<SiteDetail>?, response: Response<SiteDetail>?) {
                    val siteDetail = response?.body()

                    titleDetail.text = siteDetail?.title
                    titleDetail.changeNull()

                    addres.text = siteDetail?.address
                    addres.changeNull()

                    transport.text = siteDetail?.transport
                    transport.changeNull()

                    email.text = siteDetail?.email
                    email.changeNull()

                    geocoordinatesDetail.text = siteDetail?.geocoordinates
                    geocoordinatesDetail.changeNull()

                    description.text = siteDetail?.description
                    description.changeNull()

                    phone.text = siteDetail?.phone
                    phone.changeNull()
                }

                override fun onFailure(call: Call<SiteDetail>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }*/

}