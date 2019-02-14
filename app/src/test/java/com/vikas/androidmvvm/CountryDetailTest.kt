package com.vikas.androidmvvm

import android.app.Application
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

@RunWith(MockitoJUnitRunner::class)
class CountryDetailTest {

    lateinit var countryDetailViewModel: CountryDetailViewModel
    lateinit var countryDetailRepository: CountryDetailRepository

    @Mock
    lateinit var serviceInterface : RemoteServiceInterface
    @Mock
    lateinit var context : Application
    @Mock
    lateinit var callback : Callback<FactsResponse>
    @Mock
    lateinit var call : Call<FactsResponse>
    @Mock
    lateinit var listResponse : List<Row?>


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        countryDetailViewModel = CountryDetailViewModel(context)
        countryDetailRepository = CountryDetailRepository(serviceInterface)

    }

    @Test
    fun getCountryDetailTest() {

        Mockito.doReturn(call).`when`(countryDetailRepository.fetchCountryDetail(callback))
    }
    @Test
    fun testValidateNotNullResponseData(){

    }
}