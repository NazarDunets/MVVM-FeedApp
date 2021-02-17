package com.example.fullfledgedfeed_dunets_l25_27

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fullfledgedfeed_dunets_l25_27.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}