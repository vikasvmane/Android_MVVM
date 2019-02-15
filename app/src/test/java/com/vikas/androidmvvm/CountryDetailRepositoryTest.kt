package com.vikas.androidmvvm

import com.vikas.androidmvvm.models.dataclasses.FactsResponse
import com.vikas.androidmvvm.models.repositories.CountryDetailRepository
import com.vikas.androidmvvm.models.services.RemoteServiceInterface
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
class CountryDetailRepositoryTest {
    @Mock
    val callback: Callback<FactsResponse>? = null

    @Mock
    val call: Call<FactsResponse>? = null

    @Mock
    private val serviceInterface: RemoteServiceInterface? = null

    private var repository: CountryDetailRepository? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = CountryDetailRepository(serviceInterface!!)
    }

    @Test
    fun testFetchCountryDetail() {
        Mockito.`when`(serviceInterface?.getCountryDetails()).thenReturn(call)
        Mockito.verify(repository?.fetchCountryDetail(callback!!))?.enqueue(callback)
    }
}