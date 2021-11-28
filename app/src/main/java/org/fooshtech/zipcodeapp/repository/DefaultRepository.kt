package org.fooshtech.zipcodeapp.repository


import org.fooshtech.zipcodeapp.request.ApiService
import javax.inject.Inject

class DefaultRepository
@Inject
constructor(private val apiService: ApiService) : Repository {

    override suspend fun getData(api: String, zipCode: String, distance: String) =
        apiService.getZipCodeList(api, zipCode, distance, "km")

}
