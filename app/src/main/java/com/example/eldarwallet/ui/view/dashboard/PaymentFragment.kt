package com.example.eldarwallet.ui.view.dashboard

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eldarwallet.R
import com.example.eldarwallet.databinding.FragmentPaymentBinding
import com.example.eldarwallet.domain.model.Card
import com.example.eldarwallet.domain.model.User
import com.example.eldarwallet.ui.adapter.CardDropDownItemAdapter
import com.example.eldarwallet.ui.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentFragment : Fragment() {

    private lateinit var binding: FragmentPaymentBinding
    private val homeViewModel: DashboardViewModel by viewModels()
    private var cardSelected: Card? = null
    private lateinit var cardAdapter: CardDropDownItemAdapter
    private var user: User? = null
    private var payment = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_payment, container, false)
        binding = FragmentPaymentBinding.bind(view)

        homeViewModel.getUserData()


        initAdapter()
        initObservers()
        initListeners()
        return view
    }

    private fun initListeners() {
        binding.btnGeneratePay.setOnClickListener {
            if (user != null) {
                if (payment.isNotEmpty()) {
                    if (cardSelected != null) {
                        homeViewModel.generatePayment(user!!, payment, cardSelected!!)
                    } else {
                        Toast.makeText(
                            this.context,
                            "Tiene que seleccionar una tarjeta",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this.context,
                        "El importe es obligatorio",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    this.context,
                    "Error inesperado al generar el pago",
                    Toast.LENGTH_SHORT
                ).show()
            }


        }
    }

    private fun initObservers() {
        homeViewModel.isLoading.observe(this.viewLifecycleOwner) {
            binding.progressBar.isVisible = it
        }

        homeViewModel.userData.observe(this.viewLifecycleOwner) {
            if (it != null) {
                user = it
                cardAdapter.updateList(it.cards)
            }
        }

        homeViewModel.generatePaymentResult.observe(this.viewLifecycleOwner) {
            Toast.makeText(
                this.context,
                it,
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.etAmountValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                charSequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                payment = charSequence.toString()
            }

            override fun afterTextChanged(editable: Editable?) {
            }
        })
    }

    private fun initAdapter() {
        cardAdapter =
            CardDropDownItemAdapter(emptyList()) { cardSelected -> onCardSelected(cardSelected) }
        binding.rvCardsPayment.setHasFixedSize(true)
        binding.rvCardsPayment.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCardsPayment.adapter = cardAdapter
    }

    private fun onCardSelected(card: Card) {
        cardSelected = card
        binding.tvCardSelectedValue.text = card.pan.takeLast(3)
    }


}