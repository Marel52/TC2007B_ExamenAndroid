package com.app.tc2007b_examenandroid.di

import com.app.tc2007b_examenandroid.data.remote.api.CountryApiService
import com.app.tc2007b_examenandroid.data.repository.CountryRepositoryImpl
import com.app.tc2007b_examenandroid.domain.repository.CountryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://restcountries.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCountryApiService(retrofit: Retrofit): CountryApiService {
        return retrofit.create(CountryApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCountryRepository(impl: CountryRepositoryImpl): CountryRepository {
        return impl
    }
}