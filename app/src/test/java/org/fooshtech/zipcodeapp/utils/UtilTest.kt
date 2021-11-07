package org.fooshtech.zipcodeapp.utils


import com.google.common.truth.Truth.assertThat
import org.fooshtech.zipcodeapp.model.ZipCodeItem
import org.junit.Test

class UtilTest {

    // check if input not valid
    @Test
    fun empty_zipCodeInput_distanceInput_fields(){
        val result = Util.checkInput(
            "",
            "",
            10
        )
        assertThat(result).isFalse()
    }

    // check if input is valid
    @Test
    fun not_empty_zipCodeInput_distanceInput_fields(){
        val result = Util.checkInput(
            "66202",
            "6",
            10
        )
        assertThat(result).isTrue()
    }

    @Test
    fun empty_zipCodeInput_empty_field(){
        val result = Util.checkInput(
            "",
            "6",
            10
        )
        assertThat(result).isFalse()
    }

    @Test
    fun empty_distanceInput_field(){
        val result = Util.checkInput(
            "",
            "6",
            10
        )
        assertThat(result).isFalse()
    }

    @Test
    fun input_less_than_5_zipCode_field(){
        val result = Util.checkInput(
            "66",
            "6",
            10
        )
        assertThat(result).isFalse()
    }

    @Test
    fun input_less_than_1_distanceInput_field(){
        val result = Util.checkInput(
            "66202",
            "0",
            10
        )
        assertThat(result).isFalse()
    }

    @Test
    fun distanceInput_greater_than_maxInput_field(){
        val result = Util.checkInput(
            "66202",
            "33",
            10
        )
        assertThat(result).isFalse()
    }

    // if found will return the index otherwise will return -1
    @Test
    fun check_if_zipCode_founded_return_index(){
        val result = Util.findIndex(
            mutableListOf(
                ZipCodeItem("Mission",440.44,"KS","66202"),
                ZipCodeItem("overland Park",4.44,"KS","66201"),
                ZipCodeItem("HOOOO",44.8,"NY","66208")
            ),
            "66202"
        )
        assertThat(result).isNotEqualTo(-1)
    }

    // For the second fun
    @Test
    fun check_if_zipCode_not_founded_return_minus_1(){
        val result = Util.findIndex(
            mutableListOf(
                ZipCodeItem("Mission",440.44,"KS","66202"),
                ZipCodeItem("overland Park",4.44,"KS","66201"),
                ZipCodeItem("HOOOO",44.8,"NY","66208")
            ),
            "662002"
        )
        assertThat(result).isEqualTo(-1)
    }

    @Test
    fun check_if_list_empty_return_minus_1(){
        val result = Util.findIndex(
            mutableListOf(),
            "66202"
        )
        assertThat(result).isEqualTo(-1)
    }


}