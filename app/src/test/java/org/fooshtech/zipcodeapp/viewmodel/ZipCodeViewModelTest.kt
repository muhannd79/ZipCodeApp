package org.fooshtech.zipcodeapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.fooshtech.zipcodeapp.model.ZipCodeItem
import org.fooshtech.zipcodeapp.viewmodel.repository.FakeRepo
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Response

@RunWith(AndroidJUnit4::class)
class ZipCodeViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ZipCodeViewModel

    @Before
    suspend fun setup(){
       val fakeRepo = FakeRepo()
//        val getZipCodeList = fakeRepo.getZipCodeList("3234","66202","dasd","km")
//        viewModel = ZipCodeViewModel(Response<getZipCodeList>)

        @Test
        fun getList_returnsCurrentList(){

//            val list = mutableListOf<ZipCodeViewModel>()
//            list.add(ZipCodeItem("Mission",440.44,"KS","66202"))
//            list.add(ZipCodeItem("overland Park",466.44,"KS","66212"))
//            list.add(ZipCodeItem("Hays",4400.44,"KS","66802"))
//
//            val currentList = viewModel
//            assertThat(currentList).isEqualTo(movies)

        }
    }
}