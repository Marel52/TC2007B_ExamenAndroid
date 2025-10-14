package com.app.tc2007b_examenandroid.data.local

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesManager @Inject constructor(
    @ApplicationContext context: Context
) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("country_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_LAST_VISITED_COUNTRY = "last_visited_country"
    }

    fun saveLastVisitedCountry(countryName: String) {
        prefs.edit().putString(KEY_LAST_VISITED_COUNTRY, countryName).apply()
    }

    fun getLastVisitedCountry(): String? {
        return prefs.getString(KEY_LAST_VISITED_COUNTRY, null)
    }
}