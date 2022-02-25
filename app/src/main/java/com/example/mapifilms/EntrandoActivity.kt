package com.example.mapifilms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EntrandoActivity : AppCompatActivity() {
    private lateinit var usu:String

    lateinit var pelis:ArrayList<Pelicula>
    lateinit var recy:RecyclerView
    lateinit var con:SQLiteHelperUsuario
    private lateinit var  adapter:PeliculaAdapter

    lateinit var  toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrando)
        usu= intent.getStringExtra("usuario").toString()
        toolbar=findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val decoracion=DividerItemDecoration(this,LinearLayoutManager(this).orientation)
        con= SQLiteHelperUsuario(this)
        pelis = ArrayList()

        recy = findViewById(R.id.recy)

        recy.layoutManager= LinearLayoutManager(this)
        pelis =con.returnAllPelicula()

        adapter= PeliculaAdapter(pelis,{onItemSelected(it)})
        recy.adapter=adapter

        recy.addItemDecoration(decoracion)

    }

    fun onItemSelected(pelicula: Pelicula){
        var i= Intent(this,TrailerActivity::class.java)
        i.putExtra("trailer",pelicula.trailer)
        startActivity(i)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId){
            R.id.mispeliculas->{


                return true
            }
            R.id.cerrar->{
                UserClass.prefs.wipe()
                startActivity(Intent(this, MainActivity::class.java))
                return true
            }

            else ->return super.onOptionsItemSelected(item)
        }

    }
}