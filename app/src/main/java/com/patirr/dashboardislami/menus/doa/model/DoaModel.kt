package com.patirr.dashboardislami.menus.doa.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DoaModel(
    var title: String = "",
    var doa: String = "",
    var translate: String = "",
    var latin: String = "",
    var profile: String = ""
) : Parcelable