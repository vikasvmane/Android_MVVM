package com.vikas.androidmvvm

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.*
import com.vikas.androidmvvm.models.dataclasses.FactsResponse
import com.vikas.androidmvvm.models.dataclasses.Row
import com.vikas.androidmvvm.models.repositories.CountryDetailRepository
import com.vikas.androidmvvm.models.services.RemoteServiceInterface
import com.vikas.androidmvvm.viewmodels.CountryDetailViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class CountryDetailTest {

    lateinit var countryDetailViewModel: CountryDetailViewModel
    @Mock
    lateinit var errorObserver : Observer<String>

    var errorLiveData = MutableLiveData<String>()

    @Mock
    var countryDetailsList :MutableLiveData<List<Row?>>?=null


    @Mock
    var countryDetailRepository: CountryDetailRepository?=null

    @Mock
    lateinit var serviceInterface : RemoteServiceInterface

    @Mock
    lateinit var callback : Callback<FactsResponse>

    @Mock
    lateinit var call : Call<FactsResponse>

    @Mock
    var observer : Observer<List<Row?>>? = null

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        countryDetailViewModel = Mockito.mock(CountryDetailViewModel::class.java)
    }

    @Test
    fun testGetCountryDetail(){
        countryDetailViewModel.getCountryDetails()
       // Mockito.`when`(countryDetailRepository?.fetchCountryDetail(callback)).thenReturn(call)
        Mockito.verify(countryDetailRepository,Mockito.times(1))?.fetchCountryDetail(callback)?.enqueue(callback)
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

        countryDetailsList?.observeForever(observer!!)
        countryDetailsList?.value = apiCountryDetails.rows
        Mockito.verify(observer)?.onChanged(apiCountryDetails.rows)
    }
    @Test
    fun testHandleGetCountryDetail_Failure(){
        val response = Mockito.mock(Response::class.java)
        Mockito.doReturn(false).`when`(response).isSuccessful

      //  errorLiveData.
    }
    @Test
    fun testHandleError(){
        errorLiveData.observeForever(errorObserver)

        errorLiveData.value = "Error"
        verify(errorObserver).onChanged("Error")
    }
}