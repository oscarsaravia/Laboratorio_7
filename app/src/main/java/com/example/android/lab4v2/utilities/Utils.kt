package com.example.android.lab4v2.utilities

import android.app.Activity
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.lab4v2.Guest

class Utils {
    companion object{
        var lista: MutableList<Guest> = mutableListOf(Guest("Oscar Saravia", "21882345", "oscar44@mail.es"))
        var listaroles: MutableList<String> = mutableListOf()
        var invitados: Int = 0
        var registrados: Int = 0
        var listado: String = ""
        val spinnerOption = arrayOf("Anfitrion")
    }
}

class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)