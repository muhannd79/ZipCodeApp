package org.fooshtech.zipcodeapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import org.fooshtech.zipcodeapp.R
import org.fooshtech.zipcodeapp.adapter.ZipCodeAdapter
import org.fooshtech.zipcodeapp.databinding.ActivityMainBinding
import org.fooshtech.zipcodeapp.model.ListZipCode
import org.fooshtech.zipcodeapp.model.ZipCodeItem
import org.fooshtech.zipcodeapp.request.ApiService
import org.fooshtech.zipcodeapp.request.RetrofitInstance
import org.fooshtech.zipcodeapp.viewmodel.Resource
import org.fooshtech.zipcodeapp.viewmodel.ZipCodeViewModel
import retrofit2.Response

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var zipCodeAdapter : ZipCodeAdapter
    private val viewModel: ZipCodeViewModel by viewModels()
    private val result  = mutableListOf<ZipCodeItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding =DataBindingUtil.setContentView(this,R.layout.activity_main)
        zipCodeAdapter = ZipCodeAdapter(result)

        binding.myRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = zipCodeAdapter
        }

        binding.getDataBtn.setOnClickListener {

            val zipCodeInput : String = binding.zipCodeEditText.text.toString()
            val distanceInput : String = binding.distanceEditText.text.toString()

           if(checkInput(zipCodeInput,distanceInput)){

               viewModel.getData(
                   "QE8alngX4n945AtYr65GRnfIi9JS8wU4v4bWpDj8s2BxcSzibZPPnTLVVf1QLsoS",
                   zipCodeInput,
                   distanceInput
               )
              // setDummyData()


           } else {
               Snackbar.make(it,"Kindly Enter Correct Information",Snackbar.LENGTH_LONG).show()
           }


        }


        viewModel.zipCodeLiveData.observe(this, Observer { response ->
            when(response){
                is Resource.Success -> {
                    hideProgress()
                    response.data?.let {
                        processData(it)
                    }

                }

                is Resource.Error ->{
                    //hideprogress
                    response.message?.let {
                        Log.d("tmz","error"+it)
                    }
                }

                is Resource.Loading ->{
                    Log.d("tmz","Show Data")
                    showProgress()
                }
            }
        })

    }

    // to hid the progress
    private fun hideProgress() {
        Log.d("tmz","enter hide")
        binding.progressBar.visibility= View.INVISIBLE
    }

    // to show the progress
    private fun showProgress() {
        Log.d("tmz","enter show")
        binding.progressBar.visibility= View.VISIBLE
    }

    // check if input is correct
    private fun checkInput(zipCodeInput: String, distanceInput: String) : Boolean {

        if(zipCodeInput.isNotEmpty() && zipCodeInput.length.equals(5)
                && distanceInput.isNotEmpty() && distanceInput.length>0){
            return true
        }
        binding.zipCodeEditText.text.clear()
        binding.distanceEditText.text.clear()
        return false
    }

    // we will do processing for data
    private fun processData(it: ListZipCode) {
        zipCodeAdapter.setData(it.zipCodes)

    }

    fun setDummyData(){
        result.clear()
        result.add(ZipCodeItem("Mission",440.44,"KS","66202"))
        result.add(ZipCodeItem("overland Park",4.44,"KS","66201"))
//        result.add(ZipCodeItem("HOOOO",44,"NY","66208"))
//        result.add(ZipCodeItem("Mission",8,"UA","66209"))
//        result.add(ZipCodeItem("Mission",74,"SA","662"))
        zipCodeAdapter.setData(result)
    }

}