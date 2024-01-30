package com.example.eldarwallet.core.helpers

import android.util.Log
import java.text.NumberFormat
import java.util.Locale

class TextHelper {
    companion object{
        private val currencyFormat =  NumberFormat.getCurrencyInstance(Locale("es", "AR"))

        fun getBalanceFormat(balance: String?): String{
            if (balance.isNullOrEmpty()) return "0.00"
            try {
                val balanceValue = balance.toDouble()
                val result =  currencyFormat.format(balanceValue)
            } catch (e: NumberFormatException) {
                Log.d("getBalanceFormat", "$e")
            }
            return "0.00"
        }
    }
}