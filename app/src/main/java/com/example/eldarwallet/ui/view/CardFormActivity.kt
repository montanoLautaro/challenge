package com.example.eldarwallet.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.eldarwallet.R
import com.example.eldarwallet.core.MyToolbar
import com.example.eldarwallet.core.helpers.ValidateInputsHelper
import com.example.eldarwallet.databinding.ActivityCardFormBinding
import com.example.eldarwallet.domain.model.Card
import com.example.eldarwallet.domain.model.CardBrand
import com.example.eldarwallet.ui.view.dashboard.DashboardActivity
import com.example.eldarwallet.ui.viewmodel.CardFormViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCardFormBinding
    private val cardFormViewModel: CardFormViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCardFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MyToolbar().show(this, getString(R.string.addCard), true)

        initObservers()
        initListeners()
    }


    private fun initObservers() {
        cardFormViewModel.isLoading.observe(this) {
            binding.progressbar.isVisible = it
        }

        cardFormViewModel.isSuccess.observe(this){
            if(it){
                Toast.makeText(this, "Tarjeta guardada", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, DashboardActivity::class.java))
            }else{
                Toast.makeText(this, "Error al guardar la tarjeta", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initListeners() {
        binding.btnAddCard.setOnClickListener {
            if (validateForm()) {
                cardFormViewModel.addCard(getCard())
            }

        }
    }

    private fun getCard(): Card {
        val cardBrand = when (binding.etPan.text.toString().first()) {
            '3' -> CardBrand.AMERICAN_EXPRESS.value
            '4' -> CardBrand.VISA.value
            '5' -> CardBrand.MASTERCARD.value
            else -> ""
        }

        return Card(
            cardBrand,
            binding.etPan.text.toString(),
            binding.etCardHolder.text.toString(),
            binding.etExpirationDate.text.toString(),
            binding.etCvc.text.toString()
        )


    }

    private fun validateForm(): Boolean {
        val panMessage =
            ValidateInputsHelper.validatePanNumber(binding.etPan.text.toString())
        val cardHolderMessage =
            ValidateInputsHelper.validateNameTextInput(binding.etCardHolder.text.toString())
        val cvcMessage =
            ValidateInputsHelper.validateCvc(binding.etCvc.text.toString())
        val expirationDateMessage =
            ValidateInputsHelper.validateDate(binding.etExpirationDate.text.toString())

        return showErrorMessageText(
            panMessage,
            cardHolderMessage,
            cvcMessage,
            expirationDateMessage
        )
    }

    private fun showErrorMessageText(
        panMessage: String,
        cardHolderMessage: String,
        cvcMessage: String,
        expirationDateMessage: String,
    ): Boolean {
        binding.tvPanErrorMsg.text = panMessage
        binding.tvCardHolderErrorMsg.text = cardHolderMessage
        binding.tvExpirationErrorMsg.text = expirationDateMessage
        binding.tvCvcErrorMsg.text = cvcMessage
        return panMessage.isEmpty() && cardHolderMessage.isEmpty() && expirationDateMessage.isEmpty() && cvcMessage.isEmpty()
    }
}