package com.hex.revoluttestapplication

import android.app.Application
import android.content.Context

class AppEx : Application() {

    companion object {
        var instance: AppEx? = null

        const val PREF_CURRENCY = "pref_currency"
        const val PREF_VALUE = "pref_value"
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    var shpCurrency: String
        get()  = getSharedPreferences(instance?.packageName, Context.MODE_PRIVATE)?.getString(PREF_CURRENCY, "EUR")!!
        set(value) {
            val sPref = getSharedPreferences(instance?.packageName, Context.MODE_PRIVATE)
            val ed = sPref?.edit()
            ed?.putString(PREF_CURRENCY, value)
            ed?.apply()
        }

    var shpValue: Float
        get()  = getSharedPreferences(instance?.packageName, Context.MODE_PRIVATE)?.getFloat(PREF_VALUE, 1.0F)!!
        set(value) {
            val sPref = getSharedPreferences(instance?.packageName, Context.MODE_PRIVATE)
            val ed = sPref?.edit()
            ed?.putFloat(PREF_VALUE, value)
            ed?.apply()
        }
}
