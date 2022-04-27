package com.example.myrecyclerview

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale.filter

class MainActivity : AppCompatActivity() {
    lateinit var recycleview : RecyclerView;
    lateinit var myDB : DatabaseHandler

    lateinit var nResturant: ArrayList<Restaurants>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycleview = findViewById(R.id.recyclerView)

        myDB = DatabaseHandler(this)

        var button: Button = findViewById(R.id.button2)
        button.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
        nResturant = ArrayList<Restaurants>()

        StoreDataInArrays()
        var myadapter = MyAdapter(this,nResturant)

        recycleview.setAdapter(myadapter)
        recycleview.setLayoutManager(LinearLayoutManager(this))

        var srv: SearchView= findViewById(R.id.res_search)
        srv.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                myadapter.getFiler().filter((newText))
                return false
            }

        })

    }

    fun StoreDataInArrays(){
         nResturant = myDB.readAllData()
        }

}