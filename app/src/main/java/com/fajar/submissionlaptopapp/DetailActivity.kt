package com.fajar.submissionlaptopapp

import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ITEM = "extra_item"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val actionBar=supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        val item = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_ITEM, Laptop::class.java)
        } else {
            @Suppress("DEPRECATED")
            intent.getParcelableExtra(EXTRA_ITEM)
        }

        supportActionBar!!.title = item?.name

        val tvItemName: TextView = findViewById(R.id.tv_item_name)
        val tvItemPrice: TextView = findViewById(R.id.tv_item_price)
        val tvItemDesc: TextView = findViewById(R.id.tv_item_description)
        val imgItemPhoto: ImageView = findViewById(R.id.img_item_photo)
        if (item != null) {
            tvItemName.text = item.name
            tvItemPrice.text = item.price
            tvItemDesc.text = item.desc
            imgItemPhoto.setImageResource(item.image)
        }
        val shareButton: Button = findViewById(R.id.action_share)

        shareButton.setOnClickListener {
            val intentShare = Intent()
            intentShare.action = Intent.ACTION_SEND
            intentShare.putExtra(Intent.EXTRA_TEXT, "${item?.link}")
            intentShare.type = "text/plain"
            startActivity(Intent.createChooser(intentShare, "Bagikan link"))
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()

        return true
    }



}