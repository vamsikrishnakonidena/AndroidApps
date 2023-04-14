package com.example.mydiary

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var login=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sh=getSharedPreferences("mypref", Context.MODE_PRIVATE)
        val isloggedin=sh.getBoolean("isloggedin",false)
        val reg11=sh.getString("id","")
        if(isloggedin)
        {
         val intent=Intent(this@MainActivity,Home::class.java)
            intent.putExtra("regM",reg11)
            startActivity(intent)
        }
        setContentView(R.layout.activity_main)
        val reg=findViewById<EditText>(R.id.reg)
        val pas=findViewById<EditText>(R.id.pas)
        val btn=findViewById<Button>(R.id.btn)
        val sigt1=findViewById<TextView>(R.id.sigt1)
        val co=Db(this@MainActivity)
        sigt1.setOnClickListener(View.OnClickListener { startActivity(Intent(this@MainActivity,Sign::class.java)) })
        btn.setOnClickListener{
            val c=co.getPassword(reg.text.toString())
            c!!.moveToFirst()
            if(reg.text.toString().isEmpty() || pas.text.toString().isEmpty())
            {
                Toast.makeText(this@MainActivity,"Please Enter all the Details",Toast.LENGTH_SHORT).show()
            }
            else
            {
                if(c.count<1)
                {
                    Toast.makeText(this@MainActivity,"Please Register First",Toast.LENGTH_SHORT).show()
                    reg.text.clear()
                    pas.text.clear()
                    reg.requestFocus()
                }
                else {
                    val real=c.getString(c.getColumnIndexOrThrow("pass"))
                    if(real.toString().equals(pas.text.toString()))
                    {
                        Toast.makeText(this@MainActivity,"Welcome",Toast.LENGTH_SHORT).show()
                        val intent=Intent(this@MainActivity,Home::class.java)
                        login=1
                        intent.putExtra("regM",reg.text.toString())
                        reg.text.clear()
                        pas.text.clear()
                        reg.requestFocus()
                        startActivity(intent)
                    }
                    else
                    {
                        Toast.makeText(this@MainActivity,"Invalid details",Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }
}