package com.example.eldarwallet.core.helpers

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ValidateInputsHelper {
    companion object {
        private val regexName = Regex("^[a-zA-Z'\\sñÑáéíóúÁÉÍÓÚ]+$")

        fun validateEmailInput(email: String): String {
            if (email.isEmpty()) return "El campo es requerido"
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                    .matches()
            ) return "Ingrese un email válido"
            return ""
        }

        fun validatePasswordInput(password: String): String {
            if (password.isEmpty()) return "El campo es requerido"
            if (password.length < 3) return "Mínimo 3 caracteres"
            if (password.length > 10) return "Máximo 10 caracteres"
            return ""
        }

        fun validateNameTextInput(name: String): String {
            if (name.isEmpty()) return "El campo es requerido"
            if (!name.matches(regexName)) return "Ingrese un nombre válido"
            return ""
        }

        fun validateLastNameTextInput(lastName: String): String {
            if (lastName.isEmpty()) return "El campo es requerido"
            if (!lastName
                    .matches(regexName)
            ) return "Ingrese un apellido válido"
            return ""
        }

        fun validatePanNumber(pan: String): String {
            if (pan.isEmpty()) return "El campo es requerido"
            if (pan.first().equals("3") || pan.first().equals("4") || pan.first()
                    .equals("5")
            ) return "El número de tarjeta no es válido"
            if (pan.length < 14 || pan.length > 20) return "El número de tarjeta no es válido"
            return ""
        }

        fun validateDate(date: String): String {
            if (date.isEmpty()) return "El campo es requerido"
            if (date.length != 4) return "Ingrese una fecha válida, ej: 0124"
            if (!dateValidateFormat(date)) return "Ingrese una fecha válida, ej: 0124"
            return ""
        }

        private fun dateValidateFormat(date: String): Boolean {
            val format = SimpleDateFormat("MMyy", Locale.getDefault())
            format.isLenient = false
            try {
                val fecha = format.parse(date)
                return fecha != null && !fecha.before(Date())
            } catch (e: ParseException) {
                Log.d("dateValidateFormat", "$e")
            }
            return false
        }


        fun validateCvc(cvc: String): String {
            if (cvc.isEmpty()) return "El campo es requerido"
            if (cvc.length != 3) return "Ingrese código válido"
            return ""
        }


    }
}