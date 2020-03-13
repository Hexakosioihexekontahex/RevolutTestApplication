package com.hex.revoluttestapplication.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.Glide
import com.hex.revoluttestapplication.*
import com.hex.revoluttestapplication.net.flagImageUrl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }

    override fun configViews() {
        Glide.with(this).load(flagImageUrl(AppEx.instance!!.shpCurrency)).into(ivMainFlag)
        tvMainCurrencyName.text = AppEx.instance!!.shpCurrency
        tvMainCurrencyDescription.text = getDescription(AppEx.instance!!.shpCurrency)
        etMainValue.setText(AppEx.instance!!.shpValue.toString())

        etMainValue.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                AppEx.instance!!.shpValue = try { "$s".toFloat() } catch (e: NumberFormatException) { 0.0F }
            }
            override fun afterTextChanged(s: Editable?) { }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
        })

        with(rvCurrencies) {
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun changeMainCurrency(name: String, description: String, value: Float) {
        Glide.with(this).load(flagImageUrl(name)).into(ivMainFlag)
        tvMainCurrencyName.text = name
        tvMainCurrencyDescription.text = description
        etMainValue.setText("$value")
    }

    override fun showData(values: MutableList<Pair<String, Float>>, first: Boolean) {
        if (first) {
            val adapter =
                CurrenciesAdapter { name, description, value ->
                    changeMainCurrency(name, description, value)
                }
            adapter.items = values
            with(rvCurrencies) {
                this.adapter = adapter
            }
        } else {
            (rvCurrencies.adapter as? CurrenciesAdapter)?.items = values
            rvCurrencies.adapter?.notifyDataSetChanged()
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.CENTER, 0, 0)
        }.show()
    }
}
