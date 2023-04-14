package com.example.mydiary

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar.Tab
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity

val DB_NAME="KrishnaDataBase"
val TABLE_NAME="Students"
val COL_REG_NO="regno"
val COL_NAME="name"
val COL_PASS="pass"
val COL_TEXT="dtext"
var fl=0
class Db (var context: Context):SQLiteOpenHelper(context, DB_NAME,null,1){

    override fun onCreate(db: SQLiteDatabase?) {
        val createQ =
            "CREATE TABLE $TABLE_NAME ( $COL_REG_NO TEXT PRIMARY KEY,$COL_NAME TEXT,$COL_PASS TEXT,$COL_TEXT TEXT)"


        db?.execSQL(createQ)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun singUp(reg: String, nam: String, pas: String) {
        val db = this.writableDatabase
        val contentV = ContentValues()
        contentV.put(COL_REG_NO, reg)
        contentV.put(COL_NAME, nam)
        contentV.put(COL_PASS, pas)
        val result = db.insert(TABLE_NAME, null, contentV)
        if (result.toInt() >= (1).toInt()) {
            Toast.makeText(context, "Sign up Succeded,back to login", Toast.LENGTH_SHORT).show()
            fl=1
            val back=Another()
            back.goBack()
            val te=arrayOf<String>(reg)
            val con="$COL_REG_NO=$reg"
            db?.execSQL("INSERT INTO $TABLE_NAME ($COL_TEXT) VALUES(' ') WHERE $con")


        } else {
            Toast.makeText(context, "Sign up Failed", Toast.LENGTH_SHORT).show()
        }
    }

    fun getPassword(reg: String): Cursor {
        val db = this.readableDatabase
        val some=arrayOf<String>(reg.toString())
        return db.rawQuery("SELECT $COL_PASS FROM $TABLE_NAME WHERE $COL_REG_NO=?",some)
    }
    fun getName(reg:String):Cursor{
        val db=this.readableDatabase
        val some=arrayOf<String>(reg.toString())
        return db.rawQuery("SELECT $COL_NAME FROM $TABLE_NAME WHERE $COL_REG_NO=?",some)

    }

    fun readData(reg:String):Cursor{

        val db=this.readableDatabase
        val some=arrayOf<String>(reg.toString())
        return db.rawQuery("SELECT $COL_TEXT FROM $TABLE_NAME WHERE $COL_REG_NO=?",some)

    }

    fun writeData(reg:String,dData:String)
    {
        val db=this.writableDatabase
        var s=arrayOf<String>(dData)
        val c=ContentValues()
        c.put(COL_TEXT,dData)
        val whc="$COL_REG_NO=?"
        val wha=arrayOf<String>(reg.toString())
        val ans=db.update(TABLE_NAME,c,whc,wha)
        if(ans.toInt()<(1).toInt())
        {
            Toast.makeText(context,"Something Went Wrong",Toast.LENGTH_SHORT).show()
        }
        else
        {
            Toast.makeText(context,"Successfully Saved",Toast.LENGTH_SHORT).show()
        }


    }



}
class Another:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    fun goBack() {
        if (fl == 1) {

            startActivity(Intent(this@Another, MainActivity::class.java))

        }
    }

}

