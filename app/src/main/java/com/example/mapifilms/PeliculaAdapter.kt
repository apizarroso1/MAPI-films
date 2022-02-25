package com.example.mapifilms

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class PeliculaAdapter(val items: ArrayList<Pelicula>,private val onClickListener: (Pelicula)->Unit) : RecyclerView.Adapter<PeliculaHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PeliculaHolder(layoutInflater.inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: PeliculaHolder, position: Int) {
        holder.render(items[position],onClickListener)

    }

    override fun getItemCount(): Int = items.size
}