package com.example.android.lab4v2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.lab4v2.utilities.TextItemViewHolder
import org.w3c.dom.Text


class Adapter: RecyclerView.Adapter<Adapter.ViewHolder>(){

    var data = listOf<ItemDataClass>()
     set(value){
         field = value
         notifyDataSetChanged()
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = data.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        val res = holder.itemView.context.resources
        holder.name.text = item.text1
        holder.description.text = item.text2


        
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.nombre_rol)
        val description: TextView = itemView.findViewById(R.id.descripcion_rol)
    }
}