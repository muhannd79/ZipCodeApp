package org.fooshtech.zipcodeapp.request

import org.fooshtech.zipcodeapp.model.ListZipCode
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {


    @GET("rest/{api_key}/radius.json/{zip_code}/{distance}/{units}")
    suspend fun getZipCodeList(
        @Path(value = "api_key") key: String,
        @Path(value = "zip_code") zipCode: String,
        @Path(value = "distance") distance: String,
        @Path(value = "units") units: String,

        ): Response<ListZipCode>

}