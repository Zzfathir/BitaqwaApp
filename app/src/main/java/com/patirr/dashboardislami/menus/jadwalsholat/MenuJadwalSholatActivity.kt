package com.patirr.dashboardislami.menus.jadwalsholat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.load.model.Headers
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.patirr.dashboardislami.R
import com.patirr.dashboardislami.databinding.ActivityMenuJadwalSholatBinding
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class MenuJadwalSholatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuJadwalSholatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuJadwalSholatBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbarMenuJadwalSholat)

        initNavMenu()
    }

    private fun initNavMenu() {
        val c: Date = Calendar.getInstance().time
        val df = SimpleDateFormat("E, dd MMM", Locale.getDefault())
        val formattedDate: String = df.format(c)

        binding.tvDatePray.text = formattedDate

        initGetDataJadwalSholat(c, "Jakarta")
    }

    private fun initGetDataJadwalSholat(date: Date, city: String) {
        val df = SimpleDateFormat("yyy-MM-dd", Locale.getDefault())
        val formattedDate: String = df.format(date)

        val client = AsyncHttpClient()
        val url = "http://api.pray.zone/v2/times/day.json?city=$city&date=$formattedDate"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                binding.pbJadwalSholat.visibility = View.INVISIBLE
                val response = responseBody?.let { String(it) }

                try {
                    val responseObject = JSONObject(response)
                    val dataResult = responseObject.getJSONObject("results")
                    val dataDatetimeArray = dataResult.getJSONArray("datetime")
                    val dataObjectDatetime = dataDatetimeArray.getJSONObject(0)
                    val dataObjectTimes = dataObjectDatetime.getJSONObject("times")

                    binding.tvPrayTimeImsak.text = dataObjectTimes.getString("Imsak")
                    binding.tvPrayTimeSubuh.text = dataObjectTimes.getString("Fajr")
                    binding.tvPrayTimeSunrise.text = dataObjectTimes.getString("Imsak")
                    binding.tvPrayTimeDzuhur.text = dataObjectTimes.getString("Dzuhur")
                    binding.tvPrayTimeAshar.text = dataObjectTimes.getString("Ashar")
                    binding.tvPrayTimeMaghrib.text = dataObjectTimes.getString("Maghrib")
                    binding.tvPrayTimeIsya.text = dataObjectTimes.getString("Isya")

                    val dataObjectLocation = dataResult.getJSONObject("location")
                    binding.tvLocation.text = dataObjectLocation.getString("city")
                } catch (e: Exception) {
                    Toast.makeText(this@MenuJadwalSholatActivity, e.message, Toast.LENGTH_SHORT)
                        .show()
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                binding.pbJadwalSholat.visibility = View.INVISIBLE
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"
                }
                Toast.makeText(this@MenuJadwalSholatActivity, errorMessage, Toast.LENGTH_SHORT)
                    .show()
            }


        })

    }

}
