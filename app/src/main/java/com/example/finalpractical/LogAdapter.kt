package com.example.courage

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.finalpractical.R

class LogAdapter(private val context: Activity, private val nameArray: MutableList<String>, private val scoreArray: MutableList<String>)
    : ArrayAdapter<String>(context, R.layout.sql_data_list_view, nameArray) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.sql_data_list_view, null, true)

        val name = rowView.findViewById(R.id.nameList) as TextView
        val score = rowView.findViewById(R.id.scoreList) as TextView

        name.text = nameArray[position]
        score.text=scoreArray[position]

        return rowView
    }
}