package com.app.tc2007b_examenandroid.data.repository

import com.app.tc2007b_examenandroid.data.local.PreferencesManager
import com.app.tc2007b_examenandroid.data.mapper.toDomain
import com.app.tc2007b_examenandroid.data.remote.api.CountryApiService
import com.app.tc2007b_examenandroid.domain.model.Country
import com.app.tc2007b_examenandroid.domain.repository.CountryRepository
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val api: CountryApiService,
    private val preferencesManager: PreferencesManager
) : CountryRepository {

    override suspend fun getAllCountries(): Result<List<Country>> {
        return try {
            val response = api.getCountryList()
            val countries = response.map { it.toDomain() }
            Result.success(countries)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCountryByName(name: String): Result<Country> {
        return try {
            val response = api.getCountryByName(name)
            if (response.isNotEmpty()) {

                preferencesManager.saveLastVisitedCountry(name)
                Result.success(response.first().toDomain())
            } else {
                Result.failure(Exception("País no encontrado"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getLastVisitedCountry(): String? {
        return preferencesManager.getLastVisitedCountry()
    }
}