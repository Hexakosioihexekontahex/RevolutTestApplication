package com.hex.revoluttestapplication.net

fun flagImageUrl(code: String): String {
    return "https://www.countryflags.io/${countryCodes[code]}/shiny/64.png"
}

private val countryCodes = mapOf(
    "AUD" to "au",
    "BGN" to "bg",
    "BRL" to "br",
    "CAD" to "ca",
    "CHF" to "ch",
    "CNY" to "cn",
    "CZK" to "cz",
    "DKK" to "dk",
    "EUR" to "eu",
    "GBP" to "gb",
    "HKD" to "hk",
    "HRK" to "hr",
    "HUF" to "hu",
    "IDR" to "id",
    "ILS" to "il",
    "INR" to "in",
    "ISK" to "is",
    "JPY" to "jp",
    "KRW" to "kr",
    "MXN" to "mx",
    "MYR" to "my",
    "NOK" to "no",
    "NZD" to "nz",
    "PHP" to "ph",
    "PLN" to "pl",
    "RON" to "ro",
    "RUB" to "ru",
    "SEK" to "se",
    "SGD" to "sg",
    "THB" to "th",
    "USD" to "us",
    "ZAR" to "za"
)
