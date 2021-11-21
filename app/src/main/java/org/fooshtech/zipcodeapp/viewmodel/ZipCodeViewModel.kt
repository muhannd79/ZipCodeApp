package org.fooshtech.zipcodeapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.fooshtech.zipcodeapp.model.ListZipCode
import org.fooshtech.zipcodeapp.model.ZipCodeItem
import org.fooshtech.zipcodeapp.repository.Repository
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ZipCodeViewModel
@Inject
constructor(private val repository: Repository) : ViewModel() {

    private val _zipCodeLiveData: MutableLiveData<Resource<ListZipCode>> = MutableLiveData()
    val zipCodeLiveData: LiveData<Resource<ListZipCode>>
        get() = _zipCodeLiveData


    fun getData(api: String, zipCode: String, distance: String) = viewModelScope.launch {
            _zipCodeLiveData.postValue(Resource.Loading())
            val response = repository.getData(api, zipCode, distance)
            _zipCodeLiveData.postValue(getListOfZipCode(response))
        }


    private fun getListOfZipCode(response: Response<ListZipCode>): Resource<ListZipCode> {
        if (response.isSuccessful) {
            return Resource.Success(response.body()!!)
        }
        return Resource.Error(response.message().toString())
    }

}

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}













