package com.example.eldarwallet.ui.adapter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.eldarwallet.databinding.ItemCardPaymentBinding

import com.example.eldarwallet.domain.model.Card

class CardDropDownItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemCardPaymentBinding.bind(view)

    fun render(card: Card, onCardSelected: (Card) -> Unit) {

        Log.d("render", "entro")

        binding.tvPanValue.text = card.pan.takeLast(3)
        binding.tvCardBrand.text = card.cardBrand

        binding.root.setOnClickListener {
            onCardSelected(card)
        }

    }
}