package org.fooshtech.zipcodeapp.utils

object Util {


    // check if input Fields are correct
     fun checkInput(zipCodeInput: String, distanceInput: String,max_value : Int) : Boolean {

        if(zipCodeInput.isNotEmpty() && zipCodeInput.length.equals(5)
            && distanceInput.isNotEmpty() && distanceInput.toInt()>0 && distanceInput.toInt()<max_value){
            return true
        }
        return false
    }

}