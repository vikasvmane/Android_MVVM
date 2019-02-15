package com.vikas.androidmvvm

import com.vikas.androidmvvm.models.dataclasses.FactsResponse
import com.vikas.androidmvvm.models.dataclasses.Row
import com.vikas.androidmvvm.models.repositories.CountryDetailRepository
import com.vikas.androidmvvm.models.services.RemoteServiceInterface
import com.vikas.androidmvvm.viewmodels.CountryDetailViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class CountryDetailTest {

    lateinit var countryDetailViewModel: CountryDetailViewModel
    @Mock
    var countryDetailRepository: CountryDetailRepository?=null
    @Mock
    lateinit var serviceInterface : RemoteServiceInterface
    @Mock
    lateinit var callback : Callback<FactsResponse>
    @Mock
    lateinit var call : Call<FactsResponse>


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        countryDetailViewModel = Mockito.mock(CountryDetailViewModel::class.java)
    }

    @Test
    fun testGetCountryDetail(){
        countryDetailViewModel.getCountryDetails()
        Mockito.`when`(countryDetailRepository?.fetchCountryDetail(callback)).thenReturn(call)
        Mockito.verify(countryDetailRepository,Mockito.times(1))?.fetchCountryDetail(callback)
    }
    @Test
    fun testHandleGetCountryDetail_Success() {
        val response = Mockito.mock(Response::class.java)
        val apiCountryDetails = Mockito.mock(FactsResponse::class.java)
        Mockito.doReturn(true).`when`(response).isSuccessful
        Mockito.doReturn(apiCountryDetails).`when`(response).body()
        var list = ArrayList<Row>()
        list.add(Row("","",""))
        list.add(Row("","",""))
        list.add(Row("","",""))
        Mockito.doReturn(list).`when`(apiCountryDetails).rows
        countryDetailViewModel.countryDetailsList.value = apiCountryDetails.rows
        Mockito.verify(countryDetailViewModel.countryDetailsList,Mockito.times(1)).postValue(apiCountryDetails.rows)
    }
    @Test
    fun testHandleGetCountryDetail_Failure(){
        val response = Mockito.mock(Response::class.java)
        Mockito.doReturn(false).`when`(response).isSuccessful
      //  countryDetailViewModel.
    }
    @Test
    fun testHandleError(){
        countryDetailViewModel.errorMsg.value = "Error"
      //  Mockito.verify()
    }
}