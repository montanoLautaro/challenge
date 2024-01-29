package com.example.eldarwallet.core.helpers

import java.text.NumberFormat
import java.util.Locale

class TextHelper {
    companion object{
        private val currencyFormat =  NumberFormat.getCurrencyInstance(Locale("es", "ES"))

        fun getBalanceFormat(balance: String?): String{
            if (balance.isNullOrEmpty()) return "0.00"
            try {
                val balanceValue = balance.toDouble()
                return currencyFormat.format(balanceValue)
            } catch (e: NumberFormatException) {
                println("No se pudo convertir a Double. ¡Asegúrate de que el formato sea correcto!")
            }
            return "0.00"
        }
    }
}