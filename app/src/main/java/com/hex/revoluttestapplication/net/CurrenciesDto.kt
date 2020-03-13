package com.hex.revoluttestapplication.net

import com.google.gson.annotations.SerializedName

data class CurrenciesDto (
    @SerializedName("baseCurrency") val baseCurrency: String,
    @SerializedName("rates") val rates: Map<String, Float>
)
