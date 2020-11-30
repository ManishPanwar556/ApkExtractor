package com.example.apkextractor.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apkextractor.R

class PermissionAdapter(val list:Array<String>):RecyclerView.Adapter<PermissionAdapter.ViewHolder>() {
    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
          val view=LayoutInflater.from(parent.context).inflate(R.layout.permission_row,parent,false)
          return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
           val textView=holder.view.findViewById<TextView>(R.id.permissionTextView)
        textView.text=list.get(position)
    }

    override fun getItemCount()=list.size
}