package com.example.lab8

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.std_item_layout.view.*

class StudentsAdapter (val items: List<Employee>, val context: Context): RecyclerView.Adapter<ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view_item = LayoutInflater.from(parent.context).inflate(R.layout.std_item_layout, parent, false)
        return ViewHolder(view_item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName?.text = "Name : " + items[position].emp_name
        holder.tvGen?.text = "Gender :"+items[position].emp_gen
        holder.tvEmail?.text = "Email :"+items[position].emp_email
        holder.tvSalary?.text = "Salary : " + items[position].emp_salary.toString()


    }
}
class ViewHolder (view : View) : RecyclerView.ViewHolder(view){
    val tvName  = view.tv_name
    val tvGen = view.tv_gen
    val  tvEmail = view.tv_email
    val  tvSalary = view.tv_salary

}
