package com.example.assesmentmopro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    //disini kita membuat variable untuk recyclerview, arraylist, dan array
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<DataClass>
    lateinit var imageList:Array<Int>
    lateinit var titleList:Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        kumpulan gambar
        imageList = arrayOf(
            R.drawable.ps,
            R.drawable.lr,
            R.drawable.psc,
            R.drawable.pr,
            R.drawable.an,
            R.drawable.ae,
            R.drawable.id,
            R.drawable.ai,
            R.drawable.xd,
            R.drawable.fg,


        )
//        kumpulan title
        titleList   = arrayOf(
            "Adobe Photoshop",
            "Adobe Lightroom",
            "Adobe Photoshop Camera",
            "Adobe Premiere Rush",
            "Adobe Animate",
            "Adobe After Effects",
            "Adobe Illustrator",
            "Adobe InDesign",
            "Adobe XD",
            "Figma",

        )
        //       inisialisasi recyclerview dan mengatur layout manager dan ukuran recyclerview
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        dataList = arrayListOf<DataClass>()
        getData()

    }
    //   fungsi getData untuk mengambil data dari arraylist dan mengirimkannya ke adapter
    private fun getData(){
        for(i in imageList.indices){
            val dataClass = DataClass(imageList[i], titleList[i])
            dataList.add(dataClass)
        }
        recyclerView.adapter = AdapterClass(dataList)
    }
}