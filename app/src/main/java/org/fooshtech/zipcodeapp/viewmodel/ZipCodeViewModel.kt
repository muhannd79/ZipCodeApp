package org.fooshtech.zipcodeapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.fooshtech.zipcodeapp.model.ListZipCode
import org.fooshtech.zipcodeapp.model.ZipCodeItem
import org.fooshtech.zipcodeapp.repository.Repository
import org.fooshtech.zipcodeapp.utils.Util
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ZipCodeViewModel
@Inject
constructor(private val repository: Repository) : ViewModel() {

    private val _zipCodeLiveData: MutableLiveData<Resource<List<ZipCodeItem>>> = MutableLiveData()
    val zipCodeLiveData: LiveData<Resource<List<ZipCodeItem>>>
        get() = _zipCodeLiveData


    fun getData(api: String, zipCode: String, distance: String) = viewModelScope.launch {
        if (zipCode.isEmpty() || distance.isEmpty()) {
            _zipCodeLiveData.postValue(Resource.Error(ERRORS.FIELDS_EMPTY))
        } else {
            _zipCodeLiveData.postValue(Resource.Loading())
            val response = repository.getData(api, zipCode, distance)
            _zipCodeLiveData.postValue(getListOfZipCode(response, zipCode))
        }
    }

    private fun getListOfZipCode(response: Response<ListZipCode>,zipCode : String): Resource<List<ZipCodeItem>> {
        if (response.isSuccessful) {
            response.body()?.zipCodes.let { list ->
                    val result = list as MutableList<ZipCodeItem>
                    val index = Util.findIndex(result, zipCode)
                    if (index !=-1){
                        result.removeAt(index)
                    }
                    return Resource.Success(result)
                }
            }
        return Resource.Error(ERRORS.NetWorkError)
    }

}



sealed class Resource<T>(
    val data: T? = null,
    val message: ERRORS? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: ERRORS) : Resource<T>(null, message)
    class Loading<T> : Resource<T>()
}

// I did this for learning purpose because there is no error from the servier therfore sometimes we neeed
// to create this kind of multi error types
enum class ERRORS {
    NetWorkError,
    DeviceError,
    FIELDS_EMPTY
}













