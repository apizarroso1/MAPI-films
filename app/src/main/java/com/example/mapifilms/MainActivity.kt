package com.example.mapifilms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

//saludos

lateinit var edNickname:EditText
lateinit var edPass:EditText
lateinit var btn:Button
lateinit var mysql:SQLiteHelperUsuario

lateinit var nuevaVariable:String

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edNickname=findViewById(R.id.nickname)
        edPass=findViewById(R.id.pass)
        btn=findViewById(R.id.ingresar)

        mysql= SQLiteHelperUsuario(this)

        btn.setOnClickListener{
            var usu= edNickname.text.toString()
             var pass= edPass.text.toString()

            if(usu.isEmpty()&&pass.isEmpty()){
                Toast.makeText(this,"Rellena los campos",Toast.LENGTH_LONG).show()
            }else{
                if(mysql.getUsuario(usu,pass)){
                    var i = Intent(this, Entrando::class.java)
                    i.putExtra("usuario",usu)
                    UserClass.prefs.saveName(usu)
                    startActivity(i)

                    finish()
                }else{
                    Toast.makeText(this,"Fallo de autenticacion",Toast.LENGTH_LONG).show()
                }

            }
        }

    }
}