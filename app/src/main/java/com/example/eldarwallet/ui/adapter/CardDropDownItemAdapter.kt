package com.example.eldarwallet.ui.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eldarwallet.R
import com.example.eldarwallet.domain.model.Card


class CardDropDownItemAdapter ( private var cards: List<Card> = emptyList(), private val onCardSelected: (Card) -> Unit) :
    RecyclerView.Adapter<CardDropDownItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDropDownItemViewHolder {
        Log.d("onCreateViewHolder", "entro")
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_card_payment, parent, false)
        return CardDropDownItemViewHolder(view)
    }

    override fun getItemCount(): Int = cards.size

    override fun onBindViewHolder(holder: CardDropDownItemViewHolder, position: Int) {
        Log.d("onBindViewHolder", "entro")
        holder.render(cards[position], onCardSelected)
    }

    fun updateList(cards: List<Card>) {
        this.cards = cards
        notifyDataSetChanged()
    }

}