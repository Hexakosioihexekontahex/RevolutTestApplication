package com.hex.revoluttestapplication.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hex.revoluttestapplication.AppEx
import com.hex.revoluttestapplication.R
import com.hex.revoluttestapplication.net.flagImageUrl
import org.jetbrains.anko.find

class CurrenciesAdapter(private val onMainCurrencyChanged: ((String, String, Float) -> Unit)) : RecyclerView.Adapter<CurrenciesAdapter.ViewHolder>() {

    companion object {
        private const val ITEM_TYPE_CURRENCY = 1
    }

    var items: MutableList<Pair<String, Float>> = mutableListOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        when (viewType) {
            ITEM_TYPE_CURRENCY -> {
                val confessionView = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.item_currency, viewGroup, false)
                return ViewHolder(
                    confessionView
                )
            }
            else -> throw IllegalArgumentException("Wrong type!")
        }
    }

    override fun getItemViewType(position: Int) = if (items[position] is Pair<String, Float>) {
        ITEM_TYPE_CURRENCY
    } else {
        0
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(taskViewHolder: ViewHolder, position: Int) {
        if (getItemViewType(position) == ITEM_TYPE_CURRENCY) {
            initRow(taskViewHolder, position)
        }
    }

    private fun initRow(holder: ViewHolder, position: Int) {
        holder.currencyName.text = items[position].first
        holder.currencyDescription.text = getDescription(items[position].first)
        holder.input.setText("${items[position].second}")
        holder.input.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                AppEx.instance!!.shpCurrency = items[position].first
                AppEx.instance!!.shpValue = "${items[position].second}".toFloat()
                onMainCurrencyChanged(items[position].first, items[position].first, "${items[position].second}".toFloat())
            }
        }
        Glide.with(holder.flag.context).load(flagImageUrl(items[position].first)).into(holder.flag)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var flag = itemView.find<ImageView>(R.id.ivFlag)
        var currencyName = itemView.find<TextView>(R.id.tvCurrencyName)
        var currencyDescription = itemView.find<TextView>(R.id.tvCurrencyDescription)
        var input = itemView.find<EditText>(R.id.etValue)
    }
}
