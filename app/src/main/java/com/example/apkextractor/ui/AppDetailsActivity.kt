package com.example.apkextractor.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.apkextractor.Adapter.FragmentsAdapter
import com.example.apkextractor.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class AppDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_details)
        val packageName = getPackageNameFromIntent()

        supportActionBar?.elevation = 0f
        val appName = findViewById<TextView>(R.id.appName)
        val appLogo = findViewById<ImageView>(R.id.appLogo)
        appName.text = packageName.toString()
        Glide.with(this).load(packageManager.getApplicationIcon(packageName.toString()))
            .into(appLogo)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        tabLayout.setTabTextColors(Color.parseColor("#808080"), Color.parseColor("#ffffff"))
        val viewPager2 = findViewById<ViewPager2>(R.id.viewPager)
        viewPager2.adapter = FragmentsAdapter(this)
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            if (position == 0) {
                tab.text = "AppInfo"
            } else {
                tab.text = "Permissions"
            }

        }.attach()
    }

    private fun getPackageNameFromIntent(): String {
        return intent.extras?.get("package").toString()
    }


}