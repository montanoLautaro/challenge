package com.example.eldarwallet.ui.view.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.eldarwallet.R
import com.example.eldarwallet.databinding.ActivityDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigation()

    }


    private fun bottomNavigation() {
        binding.bottomNavigationBar.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.home -> {
                    initFragment(HomeFragment())
                    true
                }

                R.id.generateQr -> {
                    initFragment(GenerateQrFragment())
                    true
                }

                R.id.payments -> {
                    initFragment(PaymentFragment())
                    true
                }

                else -> false
            }
        }
    }


    private fun initFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(binding.frLayout.id, fragment)

            setReorderingAllowed(true)
        }
    }
}