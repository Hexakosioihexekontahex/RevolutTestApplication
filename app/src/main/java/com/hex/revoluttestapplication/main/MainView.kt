package com.hex.revoluttestapplication.main

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface MainView : MvpView {

    fun configViews()
    fun showData(values: MutableList<Pair<String, Float>>, first: Boolean)
    fun changeMainCurrency(name: String, description: String, value: Float)
    fun showMessage(message: String)
}
