package com.patirr.dashboardislami.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.patirr.dashboardislami.R
import com.patirr.dashboardislami.dashboard.model.InspirationModel

class InspirationListAdapter(private val listInspiration: ArrayList<InspirationModel>)
    : RecyclerView.Adapter<InspirationListAdapter.ListViewHolder>() {

    // class untuk mendaftarkan komponen pada layout dan memasukannya ke dalam variable
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // variable imgPhoto berisi komponen ImageView pada layout item_row_inspiration.xml
       var imgPhoto: ImageView = itemView.findViewById(R.id.iv_item_photo)

    }
    // mendaftarkan tampilan / layout untuk menampilkan data
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {

        // variable view berisi layout yang akan menampilkan data (item_row_inspiration)
       val  view: View = LayoutInflater.from(parent.context)
           .inflate(R.layout.item_row_inspiration, parent, false)
        return ListViewHolder(view)
    }

    // memasang data komponen layout
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        // memasukan data berdasarkan posisinya ke dalam varibel inspiration
        val inspiration = listInspiration[position]

        Glide.with(holder.itemView.context)
            .load(inspiration.inspirationImage)
            .into(holder.imgPhoto)
    }

    // Menentukan jumlah data yang ingin di tampilkan
    override fun getItemCount(): Int {
        return listInspiration.size
    }
}