package com.example.eldarwallet.ui.view.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.eldarwallet.R
import com.example.eldarwallet.databinding.FragmentGenerateQrBinding
import com.example.eldarwallet.ui.viewmodel.DashboardViewModel
import com.example.eldarwallet.ui.viewmodel.GenerateQrViewModel

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenerateQrFragment : Fragment() {


    private lateinit var binding: FragmentGenerateQrBinding
    private val generateQrViewModel: GenerateQrViewModel by viewModels()
    private val dashboardViewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_generate_qr, container, false)
        binding = FragmentGenerateQrBinding.bind(view)

        initListeners()
        initObservers()

        return view
    }

    private fun initListeners() {
        binding.btnGenerateQr.setOnClickListener {
            generateQrViewModel.generateQr()
        }
    }

    private fun initObservers() {
        dashboardViewModel.isLoading.observe(this.viewLifecycleOwner) {
            if (it) {
                binding.progressbar.visibility = VISIBLE
            } else {
                binding.progressbar.visibility = INVISIBLE
            }
        }

        generateQrViewModel.qr.observe(this.viewLifecycleOwner) {
            if (it != null) {
                binding.imgQr.setImageBitmap(it)
            } else {
                Toast.makeText(this.context, "Error al generar el QR", Toast.LENGTH_SHORT).show()
            }
        }

    }


}