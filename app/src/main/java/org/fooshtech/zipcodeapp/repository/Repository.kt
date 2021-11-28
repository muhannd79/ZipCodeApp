package org.fooshtech.zipcodeapp.repository

import org.fooshtech.zipcodeapp.model.ListZipCode
import retrofit2.Response

interface Repository {
    suspend fun getData(api: String, zipCode: String, distance: String) : Response<ListZipCode>
}