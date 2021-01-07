package com.fdev.technogram.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fdev.technogram.databinding.MainActivtyBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding : MainActivtyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivtyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }



}
