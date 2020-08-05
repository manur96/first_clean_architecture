package com.prueba.firstappclean.view.activity

import android.app.Activity
import android.os.Bundle
import com.prueba.data.datasource.network.api.SiteService
import com.prueba.data.model.SiteDetail
import com.prueba.firstappclean.R
import kotlinx.android.synthetic.main.activity_site_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SiteDetailActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_detail)

        getSiteById()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("http://t21services.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun getSiteById() {
        var siteDetail: SiteDetail? = null
        val service = getRetrofit().create(SiteService::class.java)
        val id = intent.getStringExtra("id")
        if (id != null) {
            service.getPointById(id).enqueue(object : Callback<SiteDetail> {
                override fun onResponse(call: Call<SiteDetail>?, response: Response<SiteDetail>?) {
                    siteDetail = response?.body()

                    titleDetail.text = siteDetail?.title

                    addres.text = siteDetail?.address
                    if (addres.text == "null")
                        addres.text = "-"

                    transport.text = siteDetail?.transport
                    if (transport.text == "null")
                        transport.text = "-"

                    email.text = siteDetail?.email
                    if (email.text == "null")
                        email.text = "-"

                    geocoordinatesDetail.text = siteDetail?.geocoordinates

                    description.text = siteDetail?.description
                    if (description.text == "null")
                        description.text = "-"
                    
                    phone.text = siteDetail?.phone
                    if (phone.text == "null")
                        phone.text = "-"
                }

                override fun onFailure(call: Call<SiteDetail>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }

}