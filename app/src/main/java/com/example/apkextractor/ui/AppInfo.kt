package com.example.apkextractor.ui

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.apkextractor.R


class AppInfo : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view=inflater.inflate(R.layout.fragment_app_info, container, false)
        val packageName=activity?.intent?.extras?.get("package").toString()
        val appInfo=activity?.packageManager?.getApplicationInfo(packageName,0)
        Log.e("Permission","$appInfo?.permission")
        return view
    }

}