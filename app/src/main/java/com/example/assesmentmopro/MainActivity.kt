package com.example.assesmentmopro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    //disini kita membuat variable untuk recyclerview, arraylist, dan array
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<DataClass>
    lateinit var imageList:Array<Int>
    lateinit var titleList:Array<String>
    // disini kita membuat variable untuk searchview dan arraylist untuk menyimpan data yang dicari
    private lateinit var searchView: SearchView
    private lateinit var searchList: ArrayList<DataClass>
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
        // mencari id searchview
        searchView = findViewById(R.id.search)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        dataList = arrayListOf<DataClass>()
        //fungsi untuk mencari data
        searchList = arrayListOf<DataClass>()
        getData()

        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            // implementasi fungsi onQueryTextSubmit dan onQueryTextChange untuk mencari data
            override fun onQueryTextSubmit(query: String?): Boolean {
                // fungsi untuk menghilangkan keyboard ketika pengguna menekan tombol cari
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // fungsi untuk mencari data yang dicari
                searchList.clear()
                // fungsi untuk mengubah data menjadi huruf kecil agar tidak case sensitive
                val searchText = newText!!.toLowerCase(Locale.getDefault())
                // membuat perkondisian ketika data yang dicari tidak kosong maka akan menampilkan data yang dicari
                // jika data yang dicari kosong maka akan menampilkan data awal
                if(searchText.isNotEmpty()){
                    dataList.forEach{
                        // fungsi contains untuk mencari data yang dicari dan mengubahnya menjadi huruf kecil
                        if(it.dataTitle.toLowerCase(Locale.getDefault()).contains(searchText)){
                            searchList.add(it)
                        }
                    }
                    // fungsi notifyDataSetChanged untuk mengupdate data yang ada di recyclerview
                    recyclerView.adapter!!.notifyDataSetChanged()
                } else {
                    searchList.clear()
                    searchList.addAll(dataList)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                return false
            }

        })

    }
    //   fungsi getData untuk mengambil data dari arraylist dan mengirimkannya ke adapter
    private fun getData(){
        for(i in imageList.indices){
            val dataClass = DataClass(imageList[i], titleList[i])
            dataList.add(dataClass)
        }
        //disini kita mengirimkan data ke adapter dan mengatur adapter ke recyclerview dengan fungsi adapter.notifyDataSetChanged()
        searchList.addAll(dataList)
        recyclerView.adapter = AdapterClass(searchList)
    }
}