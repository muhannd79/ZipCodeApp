package org.fooshtech.zipcodeapp.model


import com.google.gson.annotations.SerializedName

data class ListZipCode(
    @SerializedName("zip_codes")
    val zipCodes: List<ZipCodeItem>
)

data class ZipCodeItem(
    @SerializedName("city")
    val city: String,
    @SerializedName("distance")
    val distance: Double,
    @SerializedName("state")
    val state: String,
    @SerializedName("zip_code")
    val zipCode: String
)