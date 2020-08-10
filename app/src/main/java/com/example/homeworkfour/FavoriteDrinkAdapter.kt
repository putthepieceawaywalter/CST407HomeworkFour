package com.example.homeworkfour

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class FavoriteDrinkAdapter(
    //private var drinks: MutableList<Result?> = ArrayList(),
    private var likedDrinks: MutableList<LikedDrink>
    //private var onDrinkClick: (drinkResult: Result) -> Unit,
    //private var onDrinkClick: (drinkResult: LikedDrink) -> Unit
): RecyclerView.Adapter<FavoriteDrinkAdapter.DrinksViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteDrinkAdapter.DrinksViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.favorite_adapter_layout, parent, false)
        return DrinksViewHolder(view)

    }



    override fun getItemCount(): Int {
        return likedDrinks.size
    }

    override fun onBindViewHolder(holder: DrinksViewHolder, position: Int) {
        return holder.bind(likedDrinks[position])
    }
    inner class DrinksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val photo: ImageView = itemView.findViewById(R.id.drink_photo)
        private val title: TextView = itemView.findViewById(R.id.drink_name)


        fun bind(likedDrinks: LikedDrink) {
            Glide.with(itemView.context).load(likedDrinks.thumbnail).into(photo)
            if (likedDrinks != null) {
                title.text = likedDrinks.name
            }
//
//            itemView.setOnClickListener{
//                onDrinkClick.invoke(likedDrinks)
//            }
        }
    }
    fun appendDrinks(drinks: List<LikedDrink?>) {

        this.likedDrinks.addAll(likedDrinks)
        notifyItemRangeInserted(this.likedDrinks.size, likedDrinks.size)
    }
}