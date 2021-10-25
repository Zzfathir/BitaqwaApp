package com.patirr.dashboardislami.menus.zakat

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.patirr.dashboardislami.databinding.ActivityMenuZakatBinding
import java.text.NumberFormat
import java.util.*

class MenuZakatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuZakatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuZakatBinding.inflate(layoutInflater)
        val view =binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbarMenuZakat)
        initNavMenu()
    }
    private fun initNavMenu(){
        binding.btnZakat.setOnClickListener {
            val formatAmount = NumberFormat.getNumberInstance(Locale("id", "ID"))
                .format(binding.etAmountZakat.getNumericValue())
            binding.tvHartaAmount.text = "Rp $formatAmount"

            if (binding.etAmountZakat.getNumericValue()?.toInt()!! >= 85000000) {
                val zakat = binding.etAmountZakat.getNumericValue()!!.toInt() * (2.5 / 100)
                val formatZakat = NumberFormat.getNumberInstance(Locale("id", "ID"))
                    .format(zakat)
                binding.tvAmountZakat.text = "Rp $formatZakat"
            } else {
                Toast.makeText(
                    this, "total harta belum mencapai nisah (85gr Emas)",
                    Toast.LENGTH_LONG
                ).show()
                binding.tvAmountZakat.text = "RP 0"
            }

        }

    }

}
