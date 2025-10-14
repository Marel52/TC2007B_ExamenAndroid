package com.app.tc2007b_examenandroid.data.remote.api

import com.app.tc2007b_examenandroid.data.remote.dto.CountryDetailDto
import com.app.tc2007b_examenandroid.data.remote.dto.CountryDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CountryApiService {

    @GET("v3.1/all")
    suspend fun getCountryList(
        @Query("fields") fields: String = "name"
    ): List<CountryDto>

    @GET("v3.1/name/{name}")
    suspend fun getCountryByName(
        @Path("name") name: String
    ): List<CountryDetailDto>
}