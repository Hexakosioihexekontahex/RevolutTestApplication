package com.hex.revoluttestapplication.main

fun getDescription(code: String) = countryDescriptions[code] ?: ""

private val countryDescriptions = mapOf(
    "AUD" to "Australian dollar",
    "BGN" to "Bulgarian lev",
    "BRL" to "Brazilian real",
    "CAD" to "Canadian dollar",
    "CHF" to "Swiss franc",
    "CNY" to "Renminbi",
    "CZK" to "Czech koruna",
    "DKK" to "Danish krone",
    "EUR" to "Euro",
    "GBP" to "Pound sterling",
    "HKD" to "Hong Kong dollar",
    "HRK" to "Croatian kuna",
    "HUF" to "Hungarian forint",
    "IDR" to "Indonesian rupiah",
    "ILS" to "Israeli Shekel",
    "INR" to "Indian rupee",
    "ISK" to "Icelandic króna",
    "JPY" to "Japanese yen",
    "KRW" to "South Korean won",
    "MXN" to "Mexican peso",
    "MYR" to "Malaysian ringgit",
    "NOK" to "Norwegian krone",
    "NZD" to "New Zealand dollar",
    "PHP" to "Philippine peso",
    "PLN" to "Polish złoty",
    "RON" to "Romanian leu",
    "RUB" to "Russian Rouble",
    "SEK" to "Swedish krona",
    "SGD" to "Singapore dollar",
    "THB" to "Thai baht",
    "USD" to "US dollar",
    "ZAR" to "South African rand"
)
