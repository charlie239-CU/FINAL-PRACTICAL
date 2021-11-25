package com.example.finalpractical

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import com.example.courage.LogAdapter
import com.example.mysqliteexperiment.Student
import example.javatpoint.com.kotlinsqlitecrud.DatabaseHandler

class ResultActivity : AppCompatActivity() {
    private lateinit var listView:ListView
    private lateinit var adapter:LogAdapter
    private var nameList:MutableList<String> = mutableListOf()
    private lateinit var databaseHandler: DatabaseHandler
    private var emailList:MutableList<String> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        databaseHandler= DatabaseHandler(this)
        listView=findViewById(R.id.listView)
        getData()
        adapter = LogAdapter(this, nameList, emailList)
    }
    fun getData(){
        val list:MutableList<Student> = databaseHandler.viewstu()
        emailList.clear()
        nameList.clear()
        for(d in list){
            emailList.add(d!!.score!!)
            nameList.add(d!!.name!!)
        }
        Log.d("datalite",list.toString())
        adapter = LogAdapter(this, nameList, emailList)
        listView.adapter = adapter
        listView.deferNotifyDataSetChanged()
    }
}