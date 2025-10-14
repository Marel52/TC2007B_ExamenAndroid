package com.app.tc2007b_examenandroid.domain.model
data class Country(
    val name: Name,
    val tld: List<String>? = null,
    val cca2: String? = null,
    val ccn3: String? = null,
    val cca3: String? = null,
    val cioc: String? = null,
    val independent: Boolean? = null,
    val status: String? = null,
    val unMember: Boolean? = null,
    val currencies: Map<String, Currency>? = null,
    val idd: Idd? = null,
    val capital: List<String>? = null,
    val altSpellings: List<String>? = null,
    val region: String? = null,
    val subregion: String? = null,
    val languages: Map<String, String>? = null,
    val latlng: List<Double>? = null,
    val landlocked: Boolean? = null,
    val borders: List<String>? = null,
    val area: Double? = null,
    val demonyms: Map<String, Demonym>? = null,
    val translations: Map<String, Translation>? = null,
    val flag: String? = null,
    val maps: Maps? = null,
    val population: Long? = null,
    val gini: Map<String, Double>? = null,
    val fifa: String? = null,
    val car: Car? = null,
    val timezones: List<String>? = null,
    val continents: List<String>? = null,
    val flags: ImageSet? = null,
    val coatOfArms: ImageSet? = null,
    val startOfWeek: String? = null,
    val capitalInfo: CapitalInfo? = null,
    val postalCode: PostalCode? = null
)

data class Name(
    val common: String,
    val official: String,
    val nativeName: Map<String, NativeName>? = null
)

data class NativeName(
    val official: String,
    val common: String
)

data class Currency(
    val name: String,
    val symbol: String? = null
)

data class Idd(
    val root: String? = null,
    val suffixes: List<String>? = null
)

data class Demonym(
    val f: String? = null,
    val m: String? = null
)

data class Translation(
    val official: String,
    val common: String
)

data class Maps(
    val googleMaps: String? = null,
    val openStreetMaps: String? = null
)

data class Car(
    val signs: List<String>? = null,
    val side: String? = null
)

data class ImageSet(
    val png: String? = null,
    val svg: String? = null,
    val alt: String? = null
)

data class CapitalInfo(
    val latlng: List<Double>? = null
)

data class PostalCode(
    val format: String? = null,
    val regex: String? = null
)
