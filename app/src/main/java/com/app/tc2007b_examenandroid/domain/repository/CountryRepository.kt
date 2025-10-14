package com.app.tc2007b_examenandroid.domain.repository

import com.app.tc2007b_examenandroid.domain.model.Country

interface CountryRepository{
    suspend fun getAllCountries(): Result<List<Country>>
    suspend fun getCountryByName(name: String): Result<Country>
    fun getLastVisitedCountry(): String?
}