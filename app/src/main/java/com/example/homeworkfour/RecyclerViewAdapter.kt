package com.example.homeworkfour

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlin.reflect.KFunction0

class DrinksAdapter(
    private var drinks: MutableList<Result>,
    private var onDrinkClick: (drinkResult: Result) -> Unit
    //private val myLikedDrinks: MutableList<LikedDrinks>
): RecyclerView.Adapter<DrinksAdapter.DrinksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinksViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_item_layout, parent, false)
        return DrinksViewHolder(view)
    }

    override fun getItemCount(): Int {
        return drinks.size
    }

    override fun onBindViewHolder(holder: DrinksViewHolder, position: Int) {
        return holder.bind(drinks[position])
    }

    inner class DrinksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val photo: ImageView = itemView.findViewById(R.id.drink_photo)
        private val title: TextView = itemView.findViewById(R.id.drink_name)

//
//        fun bind(drink: Result) {
//
//
//            val toggleButton = itemView.findViewById<ToggleButton>(R.id.btn_favorite_button)
//                toggleButton.setOnCheckedChangeListener { _, isChecked ->
//
//                    val user = FirebaseAuth.getInstance().currentUser
//                    val likedDrinks = LikedDrinks(null, user!!.uid, drink.idDrink)
//                    val database = FirebaseDatabase.getInstance()
//
////                    if (isChecked) {
////                        var exists = false
////                        myLikedDrinks.forEach {
////                            if (it.drinkID == drink.idDrink) {
////                                exists = true
////                            }
////                        }
////                        if (!exists) {
////                            val newLikeReference = database.reference.child("Users").push().key
////                            likedDrinks.key = newLikeReference
////                            database.reference.child("Users").child(newLikeReference.toString())
////                                .setValue(likedDrinks)
////                            myLikedDrinks.add(likedDrinks)
////                        }
////                    } else {
////                        myLikedDrinks.forEach {
////                            if (it.drinkID == drink.idDrink) {
////                                likedDrinks.key = it.key.toString()
////                                database.reference.child("Users").child(it.key.toString()).removeValue()
////                                myLikedDrinks.remove(likedDrinks)
////                                myLikedDrinks.removeIf { liked -> liked.key.equals(it.key.toString()) }
////                            }
////                        }
////                    }
//                }
////
////            var liked = false
////            myLikedDrinks?.forEach {
////                if (drink.idDrink == it.drinkID) {
////                    liked = true
////                }
////            }
//
//
//            //toggleButton.isChecked = liked
//
//            Glide.with(itemView.context).load(drink.strDrinkThumb).into(photo)
//            title.text = drink.strDrink
//
//            itemView.setOnClickListener{
//                onDrinkClick.invoke(drink)
//            }

        fun bind(drinks: Result) {
            Glide.with(itemView.context).load(drinks.strDrinkThumb).into(photo)
            title.text = drinks.strDrink

            itemView.setOnClickListener{
                onDrinkClick.invoke(drinks)
            }
        }
    }
    fun appendDrinks(drinks: List<Result>) {
        this.drinks.addAll(drinks)
        notifyItemRangeInserted(this.drinks.size, drinks.size)
    }
}