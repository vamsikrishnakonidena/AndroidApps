package com.example.mydiary

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Home: AppCompatActivity() {
    override fun onBackPressed() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val reg=intent.getStringExtra("regM")
        val textd=findViewById<EditText>(R.id.someText)
        val btn=findViewById<Button>(R.id.sbtn)
        val cl=Db(this@Home)
        val ma=MainActivity()
        val c=cl.getName(reg.toString())
        c!!.moveToFirst()
        val textwe=findViewById<TextView>(R.id.w1)
        val btn2=findViewById<Button>(R.id.btn2)
        val c1=cl.readData(reg.toString())
        c1!!.moveToFirst()
        var str=c1.getString(c1.getColumnIndexOrThrow("dtext"))
        val t=c.getString(c.getColumnIndexOrThrow("name"))
        textd.setText(str)
        textwe.text="Welcome $t,have a nice day"

         val sh=getSharedPreferences("mypref",Context.MODE_PRIVATE)
        with (sh.edit()){
            putBoolean("isloggedin",true)
            putString("id",reg)

            apply()
        }
        btn.setOnClickListener{

            cl.writeData(reg.toString(),textd.text.toString())
        }
        btn2.setOnClickListener{
            val sh=getSharedPreferences("mypref",Context.MODE_PRIVATE)
            with (sh.edit()){
                putBoolean("isloggedin",false)
                putString("id","")
                apply()
            }
            finish()
            val intent=Intent(this@Home,MainActivity::class.java)
            Toast.makeText(this@Home,"logged out", Toast.LENGTH_SHORT).show()
            finish()
        }





    }
}