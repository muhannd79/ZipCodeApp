package org.fooshtech.zipcodeapp.viewmodel.repository

import org.fooshtech.zipcodeapp.model.ListZipCode
import org.fooshtech.zipcodeapp.model.ZipCodeItem
import org.fooshtech.zipcodeapp.request.ApiService


//class FakeRepo : ApiService {
class FakeRepo {

    val zipCodeList = mutableListOf<ZipCodeItem>()
    private var shouldReturnNetworkError = false


    init {
        zipCodeList.add(ZipCodeItem("Mission", 440.44, "KS", "66202"))
        zipCodeList.add(ZipCodeItem("overland Park", 466.44, "KS", "66212"))
        zipCodeList.add(ZipCodeItem("Hays", 4400.44, "KS", "66802"))
    }


}