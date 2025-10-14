package com.app.tc2007b_examenandroid.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CountryDto(
    @SerializedName("name")
    val name: NameDto,
)