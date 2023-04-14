package com.example.mydiary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Sign : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)
        val reg1=findViewById<EditText>(R.id.reg1)
        val nam1=findViewById<EditText>(R.id.nam1)
        val pas1=findViewById<EditText>(R.id.pas1)
        val btn1=findViewById<Button>(R.id.btn1)
        val c = Db(this@Sign)
        btn1.setOnClickListener{

            if(reg1.text.toList().isNotEmpty() && nam1.text.toString().isNotEmpty() && pas1.text.toString().isNotEmpty()) {

                c.singUp(reg1.text.toString(),nam1.text.toString(),pas1.text.toString())

            }
            else
            {
                Toast.makeText(this@Sign,"Please Fill all the Details", Toast.LENGTH_SHORT).show()
            }
            reg1.text.clear()
            nam1.text.clear()
            pas1.text.clear()


        }
    }
}