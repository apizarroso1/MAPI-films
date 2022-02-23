package com.example.mapifilms

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.practicarecufirebasesqlite.Pelicula

class SQLiteHelperUsuario(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {

    companion object  {

        private  const val DATABASE_VERSION =1
        private  const val DATABASE_NAME ="mapifilm.db"
        private  const val ID ="nickname"
        private  const val TABLAUSU="usuarios"
        private  const val PASS="pass"
        private  const val EDAD="edad"
        private const val TABLE_NAME = "peliculas"
        private const val IDPELICULA = "id"
        private const val COLUMN_TITULO = "titulo"
        private const val COLUMN_TITULO_ORIGINAL = "tituloOriginal"
        private const val COLUMN_DIRECTOR = "director"
        private const val COLUMN_DURACION = "duracion"
        private const val COLUMN_SRC = "src"
        private const val COLUMN_GENERO = "GENERO"

    }


    override fun onCreate(db: SQLiteDatabase?) {
        val crearTablaUsuario= ("CREATE TABLE "+ TABLAUSU+
                "("+ ID + " TEXT PRIMARY KEY, "+ PASS + " TEXT , $EDAD INTEGER")
        val crearTablaPelicula =
            ("CREATE TABLE $TABLE_NAME ($IDPELICULA INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_TITULO TEXT, $COLUMN_TITULO_ORIGINAL TEXT, $COLUMN_DIRECTOR TEXT, $COLUMN_DURACION INTEGER,$COLUMN_SRC TEXT,$COLUMN_GENERO TEXT")

        db?.execSQL(crearTablaPelicula)
        db?.execSQL(crearTablaUsuario)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLAUSU")
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertUsuario(usuario: Usuario): Long{
        val db= this.writableDatabase
        val contentValues= ContentValues()

        contentValues.put(ID, usuario.nickname)
        contentValues.put(PASS, usuario.pass)
        contentValues.put(EDAD, usuario.edad)

        val success= db.insert(TABLAUSU,null,contentValues)

        db.close()

        return success

    }

    fun getUsuario(nickname:String,pass:String):Boolean{
        var encontrado:Boolean = false;

        val select ="SELECT * FROM $TABLAUSU WHERE $ID= $nickname and $PASS=$pass"
        val db= this.readableDatabase

        val cursor : Cursor?


        try {
            cursor= db.rawQuery(select,null)
        }catch (e: Exception){
            e.printStackTrace()
            db.execSQL(select)
            return false
        }
        if(cursor.moveToFirst()){
            encontrado=true
        }
        return encontrado
    }

    @SuppressLint("Range")

    fun getAllUsuario(): ArrayList<Usuario> {
        val fUsuario: ArrayList<Usuario> = ArrayList()
        val selectTodos= "SELECT * FROM $TABLAUSU"
        val db= this.readableDatabase

        val cursor : Cursor?


        try {
            cursor= db.rawQuery(selectTodos,null)
        }catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectTodos)
            return ArrayList()
        }

        var id:String
        var pass:String
        var edad:Int


        if (cursor.moveToFirst()){
            do {
                id= cursor.getString(cursor.getColumnIndex(ID))
                pass=cursor.getString(cursor.getColumnIndex(PASS))
                edad=cursor.getInt(cursor.getColumnIndex(EDAD))

                val usuario= Usuario(id, pass,edad)
                fUsuario.add(usuario)
            }while (cursor.moveToNext())
        }

        return fUsuario

    }

    fun updateUsuario(usuario:Usuario) : Int{
        val db=this.writableDatabase
        val contentValues= ContentValues()
        contentValues.put(ID,usuario.nickname)
        contentValues.put(PASS,usuario.pass)
        contentValues.put(EDAD,usuario.edad)

        val exito= db.update(TABLAUSU,contentValues,"$ID= "+usuario.nickname,null)
        db.close()
        return exito

    }

    fun deleteUsuarioById(id:String):Int{
        val db=this.writableDatabase
        val contentValues= ContentValues()
        contentValues.put(ID,id)

        val exito= db.delete(TABLAUSU,"$ID= $id",null)
        db.close()


        return  exito
    }
    fun insertPelicula(p: Pelicula): Long {
        val db = this.writableDatabase
        val contentValue = ContentValues()

        contentValue.put(IDPELICULA, p.id)
        contentValue.put(COLUMN_TITULO, p.titulo)
        contentValue.put(COLUMN_TITULO_ORIGINAL, p.tituloOriginal)
        contentValue.put(COLUMN_DIRECTOR, p.director)
        contentValue.put(COLUMN_DURACION, p.duracion)
        contentValue.put(COLUMN_SRC, p.src)
        contentValue.put(COLUMN_GENERO, p.genero.toString())

        val exito = db.insert(TABLE_NAME, null, contentValue)
        db.close()

        return exito
    }

    @SuppressLint("Range")
    fun returnAllPelicula(): ArrayList<Pelicula> {
        val lista: ArrayList<Pelicula> = ArrayList()
        val statement = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(statement, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(statement)
            return ArrayList()
        }

        var id: Int
        var titulo: String
        var tituloOriginal: String
        var director: String
        var duracion: Int
        var src:String
        var genero:String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(IDPELICULA))
                titulo = cursor.getString(cursor.getColumnIndex(COLUMN_TITULO))
                tituloOriginal = cursor.getString(cursor.getColumnIndex(COLUMN_TITULO_ORIGINAL))
                director = cursor.getString(cursor.getColumnIndex(COLUMN_DIRECTOR))
                duracion = cursor.getInt(cursor.getColumnIndex(COLUMN_DURACION))
                src=cursor.getString(cursor.getColumnIndex(COLUMN_SRC))
                genero=cursor.getString(cursor.getColumnIndex(COLUMN_GENERO))
                val p = Pelicula(id, titulo, tituloOriginal, director, duracion, Genero.parse(genero),src)
                lista.add(p)
            } while (cursor.moveToNext())
        }

        db.close()

        return lista
    }

}