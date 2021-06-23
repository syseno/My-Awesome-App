package com.example.myawesomeapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.myawesomeapp.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    private var description: String? = ""
    private var imageUrl: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        intentProcess(intent)
        setSupportActionBar(toolbar)
        toolbar.title = description
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }
        Glide.with(this)
            .load(imageUrl)
            .into(news_details_thumbnail)
        detailTextView.text = description
    }

    private fun intentProcess(intent: Intent) {
        description = intent.getStringExtra(MainActivity.KEY_EXTRA_DESCRIPTION)
        imageUrl = intent.getStringExtra(MainActivity.KEY_EXTRA_IMG_URL)
    }
}