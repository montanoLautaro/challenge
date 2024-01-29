package com.example.eldarwallet.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eldarwallet.R
import com.example.eldarwallet.domain.model.Card

class CardAdapter(private var cards: List<Card> = emptyList(), private val onCardSelected: (Card) -> Unit) :
    RecyclerView.Adapter<CardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return CardViewHolder(view)
    }

    override fun getItemCount(): Int = cards.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.render(cards[position], onCardSelected)
    }

    fun updateList(cards: List<Card>) {
        this.cards = cards
        notifyDataSetChanged()
    }

}