package org.fooshtech.zipcodeapp.viewmodel.repository

import org.fooshtech.zipcodeapp.model.ListZipCode
import org.fooshtech.zipcodeapp.model.ZipCodeItem
import org.fooshtech.zipcodeapp.repository.Repository
import retrofit2.Response

//class FakeRepo : ApiService {
class FakeRepo : Repository {

    val zipCodeList = mutableListOf<ZipCodeItem>()
    val listZipCode = ListZipCode(zipCodeList)
    private var shouldReturnNetworkError = false

    init {
        zipCodeList.add(ZipCodeItem("Mission", 440.44, "KS", "66202"))
//        zipCodeList.add(ZipCodeItem("overland Park", 466.44, "KS", "66212"))
//        zipCodeList.add(ZipCodeItem("Hays", 4400.44, "KS", "66802"))
    }

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override suspend fun getData(
        api: String,
        zipCode: String,
        distance: String
    ): Response<ListZipCode> {
        if(shouldReturnNetworkError){
            return Response.success(ListZipCode(mutableListOf()))
        }
        return Response.success(listZipCode)
    }


}