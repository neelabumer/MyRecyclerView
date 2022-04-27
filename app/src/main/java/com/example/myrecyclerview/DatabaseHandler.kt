package com.example.myrecyclerview

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Filter
import android.widget.Toast

val DATABASE_NAME ="MyDB.db"
val TABLE_NAME = "Restaurants_Info"
val DB_VERSION = 1
val COL_NAME = "name"
val COL_LOCATION = "location"
val COL_PHONE = "phone"
val COL_DESCRIPTION = "description"
val COL_RATE = "rate"
class DatabaseHandler (var context : Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DB_VERSION){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =  "CREATE TABLE " + TABLE_NAME + "(" +
                COL_NAME + " TEXT," +
                COL_LOCATION + " TEXT," +
                COL_PHONE + " TEXT," +
                COL_DESCRIPTION + " TEXT," +
                COL_RATE + " TEXT" + ")";

        db?.execSQL(createTable)
 }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(" DROP TABLE IF EXISTS $TABLE_NAME ")
        onCreate(db)
    }

    fun addResturant (restaurants : Restaurants) {
        var db:SQLiteDatabase  = this.writableDatabase

        var cv : ContentValues = ContentValues()
    println("la12")
        cv.put(COL_NAME, restaurants.name)
        cv.put(COL_LOCATION,restaurants.location)
        cv.put(COL_PHONE, restaurants.phone)
        cv.put(COL_DESCRIPTION,restaurants.description)
        cv.put(COL_RATE,restaurants.rating)

        println("la34 ${restaurants.getrate()}")
        var result = db.insert(TABLE_NAME,null,cv)
        db.close()

        if(result == (-1).toLong())
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }

    fun readAllData(): ArrayList<Restaurants> {
        val db: SQLiteDatabase = this.readableDatabase
        var resArray: ArrayList<Restaurants> = ArrayList<Restaurants>()

        val query = " SELECT * FROM $TABLE_NAME "
        var cursor: Cursor? = null
        if (db != null) {
            cursor = db.rawQuery(query, null)
        }
        if (cursor != null) {
            while (cursor?.moveToNext() == true) {
                resArray.add(
                    Restaurants(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                    )
                )
            }
            println("sara read hogya ha")
        }
        return resArray
    }
    fun deleteAllData() {
        val db = this.writableDatabase
        db.execSQL(" DELETE FROM $TABLE_NAME ")
    }
}