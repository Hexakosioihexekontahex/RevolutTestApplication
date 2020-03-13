package com.hex.revoluttestapplication.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.hex.revoluttestapplication.AppEx
import com.hex.revoluttestapplication.net.CurrenciesApi
import com.hex.revoluttestapplication.net.RetrofitClient
import kotlinx.coroutines.*

@InjectViewState
class MainPresenter : MvpPresenter<MainView>(), CoroutineScope by MainScope() {

    private var job: Job? = null
    private var currentValues: MutableMap<String, Float>? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        fillCurrenciesValues()
    }

    override fun attachView(view: MainView?) {
        super.attachView(view)

        viewState.configViews()
        viewState.showData(preparedData(currentValues), true)

        job = CoroutineScope(Dispatchers.IO).launch {
            while (true) {
                delay(1000)
                fillCurrenciesValues()
                withContext(Dispatchers.Main) {
                    currentValues?.let {
                        viewState.showData(preparedData(it), false)
                    }
                }
            }
        }
        job?.start()
    }

    private fun preparedData(values: MutableMap<String, Float>?): MutableList<Pair<String, Float>> =
        values?.map { it.key to it.value * AppEx.instance!!.shpValue}?.toMutableList() ?: mutableListOf()

    private fun fillCurrenciesValues() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response =
                    RetrofitClient.instance.create(CurrenciesApi::class.java).getCurrenciesAsync(
                            AppEx.instance!!.shpCurrency)
                        .await()
                if (response.isSuccessful) {
                    if (currentValues == null) {
                        currentValues = mutableMapOf()
                    } else {
                        currentValues?.clear()
                    }
                    response.body()!!.rates.let {
                        for ((currency, value) in it) {
                            currentValues!![currency] = value
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        viewState.showMessage("failure")
                    }
                }
            } catch (e: Throwable) {
                withContext(Dispatchers.Main) {
                    viewState.showMessage("failure")
                }
            }
        }.start()
    }

    override fun detachView(view: MainView?) {
        super.detachView(view)

        job?.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()

        this.cancel()
    }
}
