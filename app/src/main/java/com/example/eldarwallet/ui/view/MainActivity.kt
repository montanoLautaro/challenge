package com.example.eldarwallet.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.eldarwallet.databinding.ActivityMainBinding
import com.example.eldarwallet.ui.view.login.SignInActivity
import com.example.eldarwallet.ui.view.login.SignUpActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
    }

    private fun initListeners() {
        binding.btnSignIn.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }

        binding.btnSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }



    }
}