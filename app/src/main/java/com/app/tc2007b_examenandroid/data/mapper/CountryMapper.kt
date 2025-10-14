package com.app.tc2007b_examenandroid.data.mapper

import com.app.tc2007b_examenandroid.data.remote.dto.*
import com.app.tc2007b_examenandroid.domain.model.*

fun CountryDto.toDomain(): Country {
    return Country(
        name = name.toDomain(),
        flags = flags?.toDomain(),
        tld = null,
        cca2 = null,
        ccn3 = null,
        cca3 = null,
        cioc = null,
        independent = null,
        status = null,
        unMember = null,
        currencies = null,
        idd = null,
        capital = null,
        altSpellings = null,
        region = null,
        subregion = null,
        languages = null,
        latlng = null,
        landlocked = null,
        borders = null,
        area = null,
        demonyms = null,
        translations = null,
        flag = null,
        maps = null,
        population = null,
        gini = null,
        fifa = null,
        car = null,
        timezones = null,
        continents = null,
        coatOfArms = null,
        startOfWeek = null,
        capitalInfo = null,
        postalCode = null
    )
}

fun CountryDetailDto.toDomain(): Country {
    return Country(
        name = name.toDomain(),
        tld = tld,
        cca2 = cca2,
        ccn3 = ccn3,
        cca3 = cca3,
        cioc = cioc,
        independent = independent,
        status = status,
        unMember = unMember,
        currencies = currencies?.mapValues { it.value.toDomain() },
        idd = idd?.toDomain(),
        capital = capital,
        altSpellings = altSpellings,
        region = region,
        subregion = subregion,
        languages = languages,
        latlng = latlng,
        landlocked = landlocked,
        borders = borders,
        area = area,
        demonyms = demonyms?.mapValues { it.value.toDomain() },
        translations = translations?.mapValues { it.value.toDomain() },
        flag = flag,
        maps = maps?.toDomain(),
        population = population,
        gini = gini,
        fifa = fifa,
        car = car?.toDomain(),
        timezones = timezones,
        continents = continents,
        flags = flags?.toDomain(),
        coatOfArms = coatOfArms?.toDomain(),
        startOfWeek = startOfWeek,
        capitalInfo = capitalInfo?.toDomain(),
        postalCode = postalCode?.toDomain()
    )
}

fun NameDto.toDomain(): Name {
    return Name(
        common = common,
        official = official,
        nativeName = nativeName?.mapValues { it.value.toDomain() }
    )
}

fun NativeNameDto.toDomain(): NativeName {
    return NativeName(
        official = official,
        common = common
    )
}

fun CurrencyDto.toDomain(): Currency {
    return Currency(
        name = name,
        symbol = symbol
    )
}

fun IddDto.toDomain(): Idd {
    return Idd(
        root = root,
        suffixes = suffixes
    )
}

fun DemonymDto.toDomain(): Demonym {
    return Demonym(
        f = f,
        m = m
    )
}

fun TranslationDto.toDomain(): Translation {
    return Translation(
        official = official,
        common = common
    )
}

fun MapsDto.toDomain(): Maps {
    return Maps(
        googleMaps = googleMaps,
        openStreetMaps = openStreetMaps
    )
}

fun CarDto.toDomain(): Car {
    return Car(
        signs = signs,
        side = side
    )
}

fun FlagsDto.toDomain(): ImageSet {
    return ImageSet(
        png = png,
        svg = svg,
        alt = alt
    )
}

fun CoatOfArmsDto.toDomain(): ImageSet {
    return ImageSet(
        png = png,
        svg = svg,
        alt = null
    )
}

fun CapitalInfoDto.toDomain(): CapitalInfo {
    return CapitalInfo(
        latlng = latlng
    )
}

fun PostalCodeDto.toDomain(): PostalCode {
    return PostalCode(
        format = format,
        regex = regex
    )
}