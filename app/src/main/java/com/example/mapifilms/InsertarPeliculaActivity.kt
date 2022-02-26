package com.example.mapifilms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar

class InsertarPeliculaActivity : AppCompatActivity() {


    lateinit var generos:Spinner
    lateinit var genero:Genero
    lateinit var titulo:EditText
    lateinit var tituloOriginal:EditText
    lateinit var duracion:EditText
    lateinit var director:EditText
    lateinit var src:EditText
    lateinit var trailer:EditText
    lateinit var btnInser:Button
    lateinit var sql:SQLiteHelperUsuario
    lateinit var  toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar_pelicula)
        toolbar=findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        initVariables()

        initSpinner()

        btnInser.setOnClickListener {
            if (titulo.equals("")&&tituloOriginal.equals("")&&src.equals("")&&trailer.equals("")){
                if(sql.insertPelicula(Pelicula(0, titulo.text.toString(), tituloOriginal.text.toString(),director.text.toString(), 100,genero,src.text.toString(),trailer.text.toString()))>-1){
                //parsear duracion
                }

            }else{

            }

        }

    }

    private fun initVariables() {
       titulo=findViewById(R.id.titulop)
        tituloOriginal=findViewById(R.id.tituloOriginalP)
        duracion=findViewById(R.id.duracionP)
       director=findViewById(R.id.directorP)
      src=findViewById(R.id.urlFoto)
       trailer=findViewById(R.id.urlVideo)
        btnInser=findViewById(R.id.insertar)
        sql= SQLiteHelperUsuario(this)
    }

    fun initSpinner() {
        generos= findViewById(R.id.generos)
        val list= Genero.values()
        val adaptador=ArrayAdapter(this,android.R.layout.simple_spinner_item,list)
        generos.adapter=adaptador

        generos.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                genero= list[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                genero = Genero.ACCION
            }

        }
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