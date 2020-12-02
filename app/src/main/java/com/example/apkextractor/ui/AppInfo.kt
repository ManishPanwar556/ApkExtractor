package com.example.apkextractor.ui

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.apkextractor.R
import java.text.SimpleDateFormat
import java.util.*


class AppInfo : Fragment() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view=inflater.inflate(R.layout.fragment_app_info, container, false)
        val packageName=activity?.intent?.extras?.get("package")
        val packageInfo=activity?.packageManager?.getPackageInfo(packageName.toString(),0)
        val dateFormat=SimpleDateFormat("EEE, MMM d, h:mm a")
        val firstInstallTime=dateFormat.format(Date(packageInfo?.firstInstallTime!!))
        val lastUpdateTime=dateFormat.format(Date(packageInfo?.lastUpdateTime))
        val targetSdkVersion= packageInfo.applicationInfo.targetSdkVersion
        val minSdkVersion=packageInfo.applicationInfo.minSdkVersion
        val processName=packageInfo.applicationInfo.processName
        val label= activity?.packageManager?.let { packageInfo.applicationInfo.loadLabel(it) }
        view.findViewById<TextView>(R.id.packageNameTextView).text=packageName.toString()
        view.findViewById<TextView>(R.id.lastUpdateTextView).text=lastUpdateTime
        view.findViewById<TextView>(R.id.minSdkVersionTextView).text=minSdkVersion.toString()
        view.findViewById<TextView>(R.id.processNameTextView).text=processName.toString()
        view.findViewById<TextView>(R.id.targetSdkTextView).text=targetSdkVersion.toString()
        view.findViewById<TextView>(R.id.firstInstallTextView).text=firstInstallTime.toString()
        view.findViewById<TextView>(R.id.appNameTextView).text=label.toString()
        return view
    }

}