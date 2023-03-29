package com.example.assesmentmopro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assesmentmopro.databinding.ActivityMainBinding
import com.google.android.material.switchmaterial.SwitchMaterial
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dataList: ArrayList<DataClass>
    private lateinit var imageList:Array<Int>
    private lateinit var titleList:Array<String>
    private lateinit var searchList: ArrayList<DataClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // disini kita membuat variable untuk switch material
        val switchMaterial = binding.switchMaterial

        switchMaterial.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        // kumpulan gambar
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
            R.drawable.fg
        )
        // kumpulan title
        titleList = arrayOf(
            "Adobe Photoshop",
            "Adobe Lightroom",
            "Adobe Photoshop Camera",
            "Adobe Premiere Rush",
            "Adobe Animate",
            "Adobe After Effects",
            "Adobe Illustrator",
            "Adobe InDesign",
            "Adobe XD",
            "Figma"
        )

        // inisialisasi recyclerview dan mengatur layout manager dan ukuran recyclerview
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)

        dataList = arrayListOf()
        // fungsi untuk mencari data
        searchList = arrayListOf()
        getData()

        binding.search.clearFocus()
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            // implementasi fungsi onQueryTextSubmit dan onQueryTextChange untuk mencari data
            override fun onQueryTextSubmit(query: String?): Boolean {
                // fungsi untuk menghilangkan keyboard ketika pengguna menekan tombol cari
                binding.search.clearFocus()
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
                    binding.recyclerView.adapter!!.notifyDataSetChanged()
                } else {
                    searchList.clear()
                    searchList.addAll(dataList)
                    binding.recyclerView.adapter!!.notifyDataSetChanged()
                }
                return false
            }

        })
    }
    //   fungsi getData untuk mengambil data dari arraylist dan mengirimkannya ke adapter
    private fun getData() {
        for (i in imageList.indices) {
            val dataClass = DataClass(imageList[i], titleList[i])
            dataList.add(dataClass)
        }
        // Mengirimkan data ke AdapterClass dengan parameter searchList yang sudah diinisialisasi pada OnCreate
        searchList.addAll(dataList)
        // Set Adapter pada RecyclerView menggunakan view binding
        binding.recyclerView.adapter = AdapterClass(searchList)
    }

}