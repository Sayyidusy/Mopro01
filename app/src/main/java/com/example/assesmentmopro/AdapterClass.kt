package com.example.assesmentmopro

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterClass(private val dataList:ArrayList<DataClass>): RecyclerView.Adapter<AdapterClass.ViewHolderClass>() {

//  fungsi onCreateViewHolder untuk membuat view holder yang akan kita gunakan untuk menampilkan data di recyclerview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolderClass(itemView)
    }

//    fungsi onBindViewHolder untuk menampilkan data yang akan kita tampilkan di recyclerview
    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]
        holder.rvImage.setImageResource(currentItem.dataImage)
        holder.rvTitle.text = currentItem.dataTitle
    }

    // fungsi getItemCount untuk menghitung jumlah data yang akan kita tampilkan di recyclerview
    override fun getItemCount(): Int {
        return dataList.size
    }

//    disini kita membuat class view holder untuk menampung data yang akan kita tampilkan di recyclerview
    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rvImage: ImageView = itemView.findViewById(R.id.image)
        val rvTitle: TextView = itemView.findViewById(R.id.title)

    }

}