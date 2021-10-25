package com.patirr.dashboardislami.dashboard.data

import com.patirr.dashboardislami.R
import com.patirr.dashboardislami.dashboard.model.InspirationModel
import java.util.ArrayList

object InspirationData{
    private val inspirationImage = intArrayOf(
        R.drawable.img_inspiration,
        R.drawable.img_inspiration
    )

    val listData: ArrayList<InspirationModel>
    get() {
        val list = arrayListOf<InspirationModel>()
        for (position in inspirationImage.indices) {
            val inspiration = InspirationModel()
            inspiration.inspirationImage = inspirationImage[position]
            list.add(inspiration)
        }
        return list

    }
}

