package com.patirr.dashboardislami.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.patirr.dashboardislami.R
import com.patirr.dashboardislami.dashboard.adapter.InspirationListAdapter
import com.patirr.dashboardislami.dashboard.data.InspirationData
import com.patirr.dashboardislami.dashboard.model.InspirationModel
import com.patirr.dashboardislami.databinding.ActivityDashboardBinding
import com.patirr.dashboardislami.menus.doa.MenuDoaActivity
import com.patirr.dashboardislami.menus.dzikir.MenuDzikirActivity
import com.patirr.dashboardislami.menus.jadwalsholat.MenuJadwalSholatActivity
import com.patirr.dashboardislami.menus.videokajian.MenuVideoKajianActivity
import com.patirr.dashboardislami.menus.zakat.MenuZakatActivity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DashboardActivity : AppCompatActivity() {

    //variable untuk mengikat layout ke class
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // mendaftarkan layout activity_dashboard.xml
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        // variavle view untuk menaampung komponen layout
        val view = binding.root
        //menentukan tampilan dengan data yang ada di variable view (binding)
        setContentView(view)

        // memanggil fungsi
        initNavMenu()
        initRecyclerViewInspiration()
        initHeader()
    }

    // fungsi untuk berpindah activity
    private fun initNavMenu(){

        // memberikan perintah klik pada komponen iv_icon_menu_doa
        binding.ivIconMenuDoa.setOnClickListener {

            // memulai activity baru dengan intent sebagai pembawa alamat activity yang ingin dituju
            startActivity(Intent(this, MenuDoaActivity::class.java))
        }

        binding.ivIconMenuDzikir.setOnClickListener {
            startActivity(Intent(this, MenuDzikirActivity::class.java))
        }

        binding.ivIconMenuJadwalSholat.setOnClickListener {
            startActivity(Intent(this, MenuJadwalSholatActivity::class.java))
        }

        binding.ivIconMenuVideoKajian.setOnClickListener {
            startActivity(Intent(this, MenuVideoKajianActivity::class.java))
        }

        binding.ivIconMenuZakat.setOnClickListener {
            startActivity(Intent(this, MenuZakatActivity::class.java))
        }

    }

    // fungsi untuk menampilkan data dari adapter ke komponen recyclerview
    private fun initRecyclerViewInspiration(){

        //variable list untuk menampung data dari object InspirationData
        val list: ArrayList<InspirationModel> = arrayListOf()
        // memasukan semua data ke dalam variable list
        list.addAll(InspirationData.listData)

        // membuat object InspirationListAdapter
        val listInspirationAdapter = InspirationListAdapter(list)
        //megantur ukuran recyclerview agar fix
        binding.rvInspiration.setHasFixedSize(true)
        //mengatur tampilan recyclerview agar sejajar secara vertical
        binding.rvInspiration.layoutManager = LinearLayoutManager(this)
        // memasang adapter ke recyclerview
        binding.rvInspiration.adapter = listInspirationAdapter
    }

    private fun initHeader(){
        // membuat variable yg menampung data waktu
        val timeNow = Calendar.getInstance()
        val timeFormat = SimpleDateFormat("HH")
        val time = timeFormat.format(timeNow.time)

        when{
            time.toInt() in 0..6 -> binding.ivHeader.setBackgroundResource(R.drawable.bg_header_dashboard_night)
            time.toInt() in 7..12 -> binding.ivHeader.setBackgroundResource(R.drawable.bg_header_dashboard_morning)
            time.toInt() in 13..18 -> binding.ivHeader.setBackgroundResource(R.drawable.bg_header_dashboard_afternoon)
            time.toInt() in 19..23 ->  binding.ivHeader.setBackgroundResource(R.drawable.bg_header_dashboard_night)

        }
    }
}
