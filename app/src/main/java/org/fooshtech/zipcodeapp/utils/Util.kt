package org.fooshtech.zipcodeapp.utils

import org.fooshtech.zipcodeapp.model.ZipCodeItem

object Util {


    // check if input Fields are correct
     fun checkInput(zipCodeInput: String, distanceInput: String,max_value : Int) : Boolean {

        if(zipCodeInput.isNotEmpty() && zipCodeInput.length.equals(5)
            && distanceInput.isNotEmpty() && distanceInput.toInt()>0 && distanceInput.toInt()<max_value){
            return true
        }
        return false
    }


    // find index for item in the array list
    fun findIndex(result: MutableList<ZipCodeItem>,zipCode : String) : Int {

        for (i in result.indices) {
            if(result[i].zipCode == zipCode){
                return i
  //  Considering that the value of the zipcode exist only once otherwise the machines will be different
            }
        }
        return -1
    }

}