package com.example.eldarwallet.ui.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.eldarwallet.R
import com.example.eldarwallet.core.MyToolbar
import com.example.eldarwallet.databinding.ActivityAddBalanceBinding
import com.example.eldarwallet.domain.model.User
import com.example.eldarwallet.ui.view.dashboard.DashboardActivity
import com.example.eldarwallet.ui.viewmodel.AddBalanceViewModel
import com.example.eldarwallet.ui.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddBalanceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBalanceBinding
    private val addBalanceViewModel: AddBalanceViewModel by viewModels()
    private val dashboardViewModel: DashboardViewModel by viewModels()
    private var balance: String = ""
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBalanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MyToolbar().show(this, getString(R.string.addBalance), true)

        dashboardViewModel.getUserData()

        initObservers()
        initListeners()
    }

    private fun initObservers() {
        addBalanceViewModel.isLoading.observe(this) {
            binding.progressBar.isVisible = it
        }

        addBalanceViewModel.isSuccess.observe(this) {
            if (it) {
                Toast.makeText(this, "Operación realizada con éxito", Toast.LENGTH_SHORT).show()
                dashboardViewModel.getUserData()
                startActivity(Intent(this, DashboardActivity::class.java))
            } else {
                Toast.makeText(this, "Error inesperado", Toast.LENGTH_SHORT).show()
            }
        }

        dashboardViewModel.userData.observe(this) {
            user = it
        }
    }

    private fun initListeners() {


        binding.etBalanceValue.addTextChangedListener(object : TextWatcher {
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
                balance = charSequence.toString()
            }

            override fun afterTextChanged(editable: Editable?) {
            }
        })




        binding.btnAddBalance.setOnClickListener {
            if (validateForm() && user != null) {
                addBalanceViewModel.addBalance(balance, user!!)
            } else {
                Toast.makeText(this, "Ingrese un monto válido", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun validateForm(): Boolean {
        if (balance.isNotEmpty()) {
            return balance.toDouble() > 0
        }
        return false
    }
}