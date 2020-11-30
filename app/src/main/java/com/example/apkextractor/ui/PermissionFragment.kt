package com.example.apkextractor.ui

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apkextractor.Adapter.PermissionAdapter
import com.example.apkextractor.R


class PermissionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_permission, container, false)
//        Toast.makeText(activity?.applicationContext,"${activity?.intent?.extras?.get("package")}",Toast.LENGTH_SHORT).show()
        val packageName = activity?.intent?.extras?.get("package")
        val packageInfo=activity?.packageManager?.getPackageInfo(packageName.toString(),PackageManager.GET_PERMISSIONS)
        val rev=view.findViewById<RecyclerView>(R.id.permRev)
        if(packageInfo?.requestedPermissions!=null){
            val list=packageInfo.requestedPermissions
            rev.adapter=PermissionAdapter(list)
            rev.layoutManager=LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        }
        else{
            Toast.makeText(activity,"Null",Toast.LENGTH_SHORT).show()
        }

        return view
    }

}