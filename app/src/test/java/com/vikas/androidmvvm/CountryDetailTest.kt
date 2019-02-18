package com.vikas.androidmvvm

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.vikas.androidmvvm.models.dataclasses.FactsResponse
import com.vikas.androidmvvm.models.dataclasses.Row
import com.vikas.androidmvvm.models.repositories.CountryDetailRepository
import com.vikas.androidmvvm.viewmodels.CountryDetailViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback

@RunWith(MockitoJUnitRunner::class)
class CountryDetailTest {

    lateinit var countryDetailViewModel: CountryDetailViewModel
    @Mock
    lateinit var errorObserver: Observer<String>

    var errorLiveData = MutableLiveData<String>()

    @Mock
    var countryDetailsList: MutableLiveData<List<Row?>>? = null


    @Mock
    var countryDetailRepository: CountryDetailRepository? = null

    @Mock
    lateinit var callback: Callback<FactsResponse>

    @Mock
    lateinit var call: Call<FactsResponse>

    @Mock
    var observer: Observer<List<Row?>>? = null

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        countryDetailViewModel = Mockito.mock(CountryDetailViewModel::class.java)
    }

    @Test
    fun testGetCountryDetail() {
        countryDetailViewModel.getCountryDetails()
        countryDetailRepository?.fetchCountryDetail(callback)
        Mockito.verify(countryDetailRepository, Mockito.times(1))?.fetchCountryDetail(callback)?.enqueue(callback)
    }

    @Test
    fun testHandleGetCountryDetail_Success() {
        countryDetailViewModel.getCountryDetails()
        countryDetailsList?.observeForever(observer!!)
        Mockito.verify(countryDetailsList,Mockito.times(1))?.observeForever(observer!!)
    }

    @Test(expected = Throwable::class)
    fun testHandleGetCountryDetail_Failure() {
        val throwable = mock(Throwable::class.java)
        countryDetailViewModel.getCountryDetails()
        `when`(countryDetailRepository?.fetchCountryDetail(callback)).thenThrow(throwable)
        Mockito.verify(errorLiveData)?.observeForever(errorObserver)
    }

    @Test
    fun testHandleError() {
        errorLiveData.observeForever(errorObserver)
        errorLiveData.value = "Error"
        verify(errorObserver).onChanged("Error")
    }
}