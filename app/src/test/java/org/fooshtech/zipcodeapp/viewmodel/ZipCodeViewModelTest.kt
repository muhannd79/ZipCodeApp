package org.fooshtech.zipcodeapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.fooshtech.zipcodeapp.model.ZipCodeItem
import org.fooshtech.zipcodeapp.viewmodel.repository.FakeRepo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class ZipCodeViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: ZipCodeViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = ZipCodeViewModel(FakeRepo())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun check_for_empty_fields() {
        viewModel.getData("11323", "7", "")
        val result = viewModel.zipCodeLiveData.getOrAwaitValueTest()
        assertThat(result.message).isEqualTo(ERRORS.FIELDS_EMPTY)
    }

    @Test
    fun check_for_success_network_call(){
        viewModel.getData("112","9","6")
        val result = viewModel.zipCodeLiveData.getOrAwaitValueTest()
        assertThat(result.data).containsAnyIn(mutableListOf<ZipCodeItem>(ZipCodeItem("Mission", 440.44, "KS", "66202")))
    }

    @Test
    fun check_for_not_contain_66202_success_network_call(){
        viewModel.getData("112","66202","6")
        val result = viewModel.zipCodeLiveData.getOrAwaitValueTest()
        assertThat(result.data).doesNotContain(mutableListOf<ZipCodeItem>(ZipCodeItem("Mission", 440.44, "KS", "66202")))
    }

}


