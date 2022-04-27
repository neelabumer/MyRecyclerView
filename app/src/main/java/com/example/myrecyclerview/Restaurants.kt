package com.example.myrecyclerview

import android.provider.ContactsContract

class Restaurants {

    var name : String = ""
    var location : String = ""
    var phone : String = ""
    var description : String = ""
    var rating : String = ""

    constructor(n: String, locat: String, ph: String, des: String, r: String){
        this.name = n
        this.location = locat
        this.phone = ph
        this.description = des
        rating = r
    }
    fun setname(n:String)
    {
        this.name = n
    }
    fun setlocation(n:String)
    {
        this.location = n
    }
    fun setphone(n:String)
    {
        this.phone = n
    }
    fun setdes(n:String)
    {
        this.description = n
    }
    fun setrate(n:String)
    {
        this.rating = n
    }
    fun getrate(): String {
        return this.rating
    }

    fun getname(): String {
        return this.name
    }
    fun getlocation(): String {
        return this.location
    }
    fun getphone(): String {
        return this.phone
    }
    fun getdes(): String {
        return this.description
    }
}