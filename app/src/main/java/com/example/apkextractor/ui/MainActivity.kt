package com.example.apkextractor.ui

import android.content.Intent
import android.content.pm.PackageInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apkextractor.Adapter.ClickInterface
import com.example.apkextractor.Adapter.RecyclerAdapter
import com.example.apkextractor.R
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), ClickInterface {
    companion object {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rev = findViewById<RecyclerView>(R.id.rev)
//        GlobalScope.launch(Dispatchers.IO) {
//            listOfPackages.forEach {
//                var name=it.applicationInfo.loadLabel(packageManager)
//                var logo=it.applicationInfo.loadLogo(packageManager)
//                list.add(AppList(name.toString(),logo))
//            }
//        }
//
//        if(list!=null){
//            rev.adapter=RecyclerAdapter(list)
//            rev.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
//        }
        GlobalScope.launch(Dispatchers.IO) {
            val list = getAllPackages()
            withContext(Dispatchers.Main) {
                val adapterList = ArrayList<AppList>()
                list.forEach {
                    adapterList.add(
                        AppList(
                            it.applicationInfo.loadLabel(packageManager).toString(),
                            it.applicationInfo.loadIcon(packageManager),
                            it
                        )
                    )
                }
                rev.adapter = RecyclerAdapter(adapterList,this@MainActivity)
                rev.layoutManager =
                    LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            }
        }
    }

    private suspend fun getAllPackages(): MutableList<PackageInfo> {

        val list = GlobalScope.async {
            packageManager.getInstalledPackages(0)
        }
        return list.await()
    }

    override fun click(appList: AppList) {
        val intent= Intent(this, AppDetailsActivity::class.java)
        intent.putExtra("package",appList.packageInfo.packageName)
        startActivity(intent)
    }


}

