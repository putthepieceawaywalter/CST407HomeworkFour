package com.example.homeworkfour

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
    private val likedDrinks: MutableList<LikedDrink>
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

        fun bind(drinks: LikedDrink) {

            val removeButton = itemView.findViewById<Button>(R.id.btn_remove_favorite)
            removeButton.setOnClickListener {
                val user = FirebaseAuth.getInstance().currentUser
                val database = FirebaseDatabase.getInstance()
                //val likedDrink = LikedDrink(null, user!!.uid, drinks?.drinkID, drinks?.name, drinks?.thumbnail)

                var position: Int = 0
                likedDrinks.forEach {
                    if (drinks != null) {
                        if (it.drinkID == drinks.drinkID) {
                            database.reference.child("Users").child(user!!.uid).child("LikedDrinks").child(it.key.toString()).removeValue()
                            position = adapterPosition
                        }
                    }
                }
                likedDrinks.removeAt(position)
                notifyItemRemoved(position)
            }
            Glide.with(itemView.context).load(drinks.thumbnail).into(photo)
            if (likedDrinks != null) {
                title.text = drinks.name
            }
        }
    }
    fun appendDrinks(drinks: List<LikedDrink?>) {

        this.likedDrinks.addAll(likedDrinks)
        notifyItemRangeInserted(this.likedDrinks.size, likedDrinks.size)
    }

}

