package com.example.mapifilms


import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

import com.squareup.picasso.Picasso

class PeliculaHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val imagen: ImageView = view.findViewById(R.id.imagen)
    private val titulo: TextView = view.findViewById(R.id.titulo)
    private val tituloOriginal: TextView = view.findViewById(R.id.tituloOriginal)
    private val director: TextView = view.findViewById(R.id.director)
    private val duracion: TextView = view.findViewById(R.id.duracion)
    private val genero: TextView = view.findViewById(R.id.generos)


    fun render(p: Pelicula, onClickListener: (Pelicula)->Unit) {
        Picasso.with(view.context).load(p.src).into(imagen);
        titulo.text = p.titulo
        tituloOriginal.text = p.tituloOriginal
        director.text = p.director
        duracion.text = p.duracion.toString()
        genero.text=p.genero.toString()
        itemView.setOnClickListener { onClickListener(p) }
        /*
        view.setOnClickListener{
            UserClass.prefs.getNickname()
            var i= Intent(view.context.applicationContext,TrailerActivity::class.java)
            i.putExtra("trailer",p.trailer)
            startActivity(i)

           /*
            var i = Intent(this, EntrandoActivity::class.java)
            i.putExtra("usuario",usu)
            UserClass.prefs.saveName(usu)
            startActivity(i)

            finish()

            */
        }*/

    }


}