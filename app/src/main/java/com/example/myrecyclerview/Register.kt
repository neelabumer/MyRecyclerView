package com.example.myrecyclerview

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Register : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val  context = this

        var b: Button = findViewById(R.id.addb)
        var b1: Button = findViewById(R.id.button3)

        var v1 : EditText = findViewById(R.id.name)
        var v2 :EditText = findViewById(R.id.location)
        var v3 :EditText = findViewById(R.id.phone)
        var v4 :EditText = findViewById(R.id.description)
        var t1 : TextView = findViewById(R.id.textView3)
        val random1: Int = (2..5).random() //generate random numbers from 2-5
        var star: String =""
        when (random1) {
            2 -> star = "**"
            3 -> star = "***"
            4 -> star = "****"
            else -> star = "*****"
        }
        t1.setText(Integer.toString(random1) +star)
        b.setOnClickListener {
            var cv = ContentValues()
                val resturants = Restaurants(v1.getText().toString(),v2.getText().toString(),v3.getText().toString(),v4.getText().toString(),t1.getText().toString())
                val db = DatabaseHandler(context)
                db.addResturant(resturants)
        }
        b1.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }
}