package com.example.myrecyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.widget.Filter
import android.widget.Filterable
class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    lateinit var listofrestaurants: ArrayList<Restaurants>
    lateinit var restListFilter: ArrayList<Restaurants>
    lateinit var context : Context

    constructor (ct: Context, s1: ArrayList<Restaurants>)
    {
        context = ct
        listofrestaurants = s1
        restListFilter = listofrestaurants
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var inflater : LayoutInflater = LayoutInflater.from(context)
        var view : View = inflater.inflate(R.layout.my_row, parent ,false)
        var ret = MyViewHolder(view)
        return ret
    }
    fun getFiler(): Filter{
        return object : Filter(){
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charString = p0?.toString() ?: ""

                if(charString.isEmpty())
                    restListFilter.addAll(listofrestaurants)
                else{
                    val filteredlist : ArrayList<Restaurants> = ArrayList<Restaurants>()
                    listofrestaurants.filter {
                        (it.name.toLowerCase().contains(p0.toString())) or (it.location.toLowerCase().contains(p0.toString())) or (it.phone.toLowerCase().contains(p0.toString())) or (it.description.toLowerCase().contains(p0.toString())) or (it.rating.toLowerCase().contains(p0.toString()))
                    }
                        .forEach{
                            filteredlist.add(it)
                        }
                    restListFilter = filteredlist
                }
                val filterres = FilterResults()
                filterres.values = restListFilter
                return filterres
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                restListFilter = p1?.values as ArrayList<Restaurants>
                notifyDataSetChanged()
            }

        }
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var rest: Restaurants = restListFilter[position]

        holder.setIsRecyclable(false)

        holder.txt1.setText(rest.getname())
        holder.txt2.setText(rest.getlocation())
        holder.txt3.setText(rest.getphone())
        holder.txt4.setText(rest.getdes())
        holder.txt5.setText(rest.getrate())

    }

    override fun getItemCount(): Int {
        return restListFilter.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
        return position
    }

    public class MyViewHolder : RecyclerView .ViewHolder {
        lateinit var txt1: TextView
        lateinit var txt2: TextView
        lateinit var txt3: TextView
        lateinit var txt4: TextView
        lateinit var txt5: TextView
        lateinit var itemView: View

        constructor(itemView:View ) : super(itemView) {
            txt1 = itemView.findViewById(R.id.txt_name)
            txt2 = itemView.findViewById(R.id.txt_location)
            txt3 = itemView.findViewById(R.id.txt_phone)
            txt4 = itemView.findViewById(R.id.txt_description)
            txt5 = itemView.findViewById(R.id.txt_rating)
        }

    }
}