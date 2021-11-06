package org.fooshtech.zipcodeapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import org.fooshtech.zipcodeapp.R
import org.fooshtech.zipcodeapp.model.ListZipCode
import org.fooshtech.zipcodeapp.request.ApiService
import org.fooshtech.zipcodeapp.request.RetrofitInstance
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var retService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(ApiService::class.java)


        getRequestWithQueryParameters()
    }

    private fun getRequestWithQueryParameters() {
        val responseLiveData: LiveData<Response<ListZipCode>> = liveData {
            val response = retService.getZipCodeList("DemoOnly001T4hfbXmr4yopWvjqP0NQhG8hzWKWHqKzqroMfVuezLgBn4FyfAwvw",66202,2,"km")
            emit(response)
        }
        responseLiveData.observe(this, Observer {
            Log.d("tmz","the result id==="+it.isSuccessful)
            val response =it.body()
            Log.d("tmz","the result="+response)
        })

    }
}