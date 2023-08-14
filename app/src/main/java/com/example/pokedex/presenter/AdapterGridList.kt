package com.example.pokedex.presenter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R

class AdapterGridList(private val names: List<String>) :
    RecyclerView.Adapter<ViewHolderGridList>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderGridList {

        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.grid_layout_list_item, parent, false)

        return ViewHolderGridList(view)
    }

    override fun getItemCount(): Int {
        return names.size
    }

    override fun onBindViewHolder(holder: ViewHolderGridList, position: Int) {
        val item = names[position]
        holder.bind(item)
    }
}

class ViewHolderGridList(view: View) : RecyclerView.ViewHolder(view) {

    val tvnumber = view.findViewById<TextView>(R.id.tv_numberpokemon)
    val tvname = view.findViewById<TextView>(R.id.tv_namepokemon)

    fun bind(name: String) {
        tvname.text = name
    }

}