package org.fooshtech.zipcodeapp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import org.fooshtech.zipcodeapp.model.ZipCodeItem
import org.fooshtech.zipcodeapp.request.ApiService
import javax.inject.Inject

class Repository
@Inject
constructor(private val apiService: ApiService) {

    suspend fun getData(api: String, zipCode: String, distance: String) =
        apiService.getZipCodeList(api, zipCode, distance, "km")

}
