package org.fooshtech.zipcodeapp.utils


import com.google.common.truth.Truth.assertThat
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


}