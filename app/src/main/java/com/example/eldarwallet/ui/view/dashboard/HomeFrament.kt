package com.example.eldarwallet.ui.view.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eldarwallet.R
import com.example.eldarwallet.core.helpers.TextHelper
import com.example.eldarwallet.databinding.FragmentHomeBinding
import com.example.eldarwallet.domain.model.Card
import com.example.eldarwallet.ui.adapter.CardAdapter
import com.example.eldarwallet.ui.view.AddBalanceActivity
import com.example.eldarwallet.ui.view.CardFormActivity
import com.example.eldarwallet.ui.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var cardAdapter: CardAdapter
    private val homeViewModel: DashboardViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.bind(view)
        homeViewModel.getUserData()

        initObservers()
        initAdapter()
        initListeners()

        return view
    }

    private fun initListeners() {
        binding.btnGoToAddCard.setOnClickListener {
            startActivity(Intent(this.context, CardFormActivity::class.java))
        }
        binding.btnGoToAddBalance.setOnClickListener {
            startActivity(Intent(this.context, AddBalanceActivity::class.java))
        }


    }

    private fun initObservers() {
        homeViewModel.isLoading.observe(this.viewLifecycleOwner) {
            binding.progressBar.isVisible = it
        }

        homeViewModel.userData.observe(this.viewLifecycleOwner) {
            if (it != null) {
                updateListAdapter(it.cards)
                binding.tvBalanceValue.text = TextHelper.getBalanceFormat(it.balance)
            }
        }


    }

    private fun initAdapter() {
        cardAdapter =
            CardAdapter(emptyList()) { cardSelected -> onItemSelected(cardSelected) }
        binding.rvCards.setHasFixedSize(true)
        binding.rvCards.layoutManager = LinearLayoutManager(this.context)
        binding.rvCards.adapter = cardAdapter
    }


    private fun updateListAdapter(cards: List<Card>) {
        cardAdapter.updateList(cards)
    }


    private fun onItemSelected(card: Card) {
        //todo editar la tarjeta
    }


}