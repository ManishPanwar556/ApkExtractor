package com.example.apkextractor.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apkextractor.ui.AppList
import com.example.apkextractor.R

class RecyclerAdapter(var list: List<AppList>, val clickInterface: ClickInterface) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    clickInterface.click(list[adapterPosition])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.app_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val appLogo = holder.view.findViewById<ImageView>(R.id.AppLogo)
        val appName = holder.view.findViewById<TextView>(R.id.AppName)
        val packageName = holder.view.findViewById<TextView>(R.id.packageName)
        Glide.with(holder.view).load(list[position].icon).into(appLogo)
        appName.text = list[position].name
        packageName.text=list[position].packageInfo.packageName
    }

    override fun getItemCount() = list.size
}