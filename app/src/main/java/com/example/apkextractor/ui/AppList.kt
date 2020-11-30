package com.example.apkextractor.ui

import android.content.pm.PackageInfo
import android.graphics.drawable.Drawable

data class AppList(
    val name:String,
    val icon:Drawable,
    val packageInfo: PackageInfo
)