package com.fajar.submissionlaptopapp

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvLaptop: RecyclerView
    private val list = ArrayList<Laptop>()
    private fun showSelectedItem(laptop: Laptop) {
        //TODO implicit intent switch to detail activity of selected intent
        Toast.makeText(this, "Anda memilih " + laptop.name, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Laptop"

        rvLaptop = findViewById(R.id.rv_laptop)
        rvLaptop.setHasFixedSize(true)

        list.addAll(getListLaptop())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        selectMenu(item.itemId)
        return super.onOptionsItemSelected(item)
    }
    private fun selectMenu(selectedMenu: Int) {
        when (selectedMenu) {
            R.id.about -> {
                val intentAbout =Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intentAbout)
            }
        }
    }
    private fun showRecyclerList() {
        rvLaptop.layoutManager = LinearLayoutManager(this)
        val listSmartphoneAdapter = ListLaptopAdapter(list)
        rvLaptop.adapter = listSmartphoneAdapter

        listSmartphoneAdapter.setOnItemClickCallback(object :
            ListLaptopAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Laptop) {
                showSelectedItem(data)
                val item = Laptop(data.name, data.price, data.desc, data.image, data.link)
                val moveObjIntent = Intent(this@MainActivity, DetailActivity::class.java)
                moveObjIntent.putExtra(DetailActivity.EXTRA_ITEM, item)
                startActivity(moveObjIntent)
            }
        })
    }

    @SuppressLint("Recycle")
    private fun getListLaptop(): ArrayList<Laptop> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataPrice = resources.getStringArray(R.array.data_price)
        val dataDesc = resources.getStringArray(R.array.data_description)
//        val dataSpecification = resources.getStringArray(R.array.data_specification)
        val dataImage = resources.obtainTypedArray(R.array.data_image)
        val dataLink = resources.getStringArray(R.array.data_link)
        val listLaptop = ArrayList<Laptop>()
        for (i in dataName.indices) {
            val smartphone = Laptop(
                dataName[i],
                dataPrice[i],
                dataDesc[i],
                dataImage.getResourceId(i, -1),
                dataLink[i]
            )
            listLaptop .add(smartphone)
        }
        return listLaptop
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to exit?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                finish()
            }
            .setNegativeButton("No") { dialog, id ->
                dialog.cancel()
            }
        val alert = builder.create()
        alert.show()
    }
}