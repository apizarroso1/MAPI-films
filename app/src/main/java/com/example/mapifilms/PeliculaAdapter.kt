package com.example.practicarecufirebasesqlite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mapifilms.R

class PeliculaAdapter(val items: ArrayList<Pelicula>) : RecyclerView.Adapter<PeliculaHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PeliculaHolder(layoutInflater.inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: PeliculaHolder, position: Int) {
        holder.render(items[position])

    }

    override fun getItemCount(): Int = items.size
}