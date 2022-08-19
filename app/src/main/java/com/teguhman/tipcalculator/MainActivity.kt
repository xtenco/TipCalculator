package com.teguhman.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.teguhman.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener{ calculateTip() }
    }

    fun calculateTip() {

        //Initialize View
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDouble()
        val selectedId = binding.tipOptions.checkedRadioButtonId

        //Define Tip Percentage
        val tipPercentage = when (selectedId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        //Calculate the Tip and Check For Round Up
        var tip = tipPercentage * cost
        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }

        //Format Tip String
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}