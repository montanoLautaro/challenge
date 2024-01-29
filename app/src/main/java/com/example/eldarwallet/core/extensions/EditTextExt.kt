package com.example.eldarwallet.core.extensions

import android.widget.EditText
import com.example.eldarwallet.core.extensions.dismissKeyboard

fun EditText.loseFocusAfterAction(action: Int){
    this.setOnEditorActionListener { view,actionId, _ ->
        if (actionId == action){
            this.dismissKeyboard()
            view.clearFocus()
        }
        return@setOnEditorActionListener false
    }
}