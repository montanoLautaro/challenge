package com.example.eldarwallet.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.eldarwallet.core.helpers.TextHelper
import com.example.eldarwallet.databinding.ItemCardBinding
import com.example.eldarwallet.domain.model.Card

class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemCardBinding.bind(view)

    fun render(card: Card, onCardSelected: (Card) -> Unit) {

        binding.tvBankBrandValue.text = card.cardBrand
        binding.tvPanValue.text = card.pan
        binding.tvExpValue.text = TextHelper.getDate(card.expirationDate)

        binding.root.setOnClickListener {
            onCardSelected(card)
        }
    }


}