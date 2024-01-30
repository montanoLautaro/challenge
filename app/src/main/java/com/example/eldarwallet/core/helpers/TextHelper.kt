package com.example.eldarwallet.core.helpers

import android.util.Log
import java.text.NumberFormat
import java.util.Locale

class TextHelper {
    companion object {
        private val currencyFormat = NumberFormat.getCurrencyInstance(Locale("es", "AR"))

        fun getBalanceFormat(balance: String?): String {
            if (balance.isNullOrEmpty()) return "0.00"
            try {
                val balanceValue = balance.toDouble()
                return currencyFormat.format(balanceValue).removeRange(0, 1)


            } catch (e: NumberFormatException) {
                Log.d("getBalanceFormat", "$e")
            }
            return "0.00"
        }

        fun getDate(date: String): String {

            val firstPart = date.substring(0, 2)
            val secondPart = date.substring(2, 4)

            return "$firstPart/$secondPart"
        }

    }
}