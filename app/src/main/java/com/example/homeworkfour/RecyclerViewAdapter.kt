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

class DrinksAdapter(
    private var drinks: MutableList<Result?>,
    private var onDrinkClick: (drinkResult: Result) -> Unit,
    private val myLikedDrinks: MutableList<LikedDrink>
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

        fun bind(drinks: Result?) {
            val toggleButton = itemView.findViewById<ToggleButton>(R.id.favorite_this_drink)
            toggleButton.setOnCheckedChangeListener {_, isChecked ->
                val user = FirebaseAuth.getInstance().currentUser
                val likedDrink = LikedDrink(null, user!!.uid, drinks?.idDrink, drinks?.strDrink, drinks?.strDrinkThumb)
                val database = FirebaseDatabase.getInstance()

                if (isChecked) {
                        var exists = false
                        myLikedDrinks.forEach {
                            if (drinks != null) {
                                if (it.drinkID == drinks.idDrink) {
                                    exists = true
                                }
                            }
                        }
                        if (!exists) {
                            val newLikeReference = database.reference.child("Users").child(user.uid).child("LikedDrinks").push().key
                            likedDrink.key = newLikeReference

                            database.reference.child("Users").child(user.uid).child("LikedDrinks").child(newLikeReference.toString()).setValue(likedDrink)
                            myLikedDrinks.add(likedDrink)
                        }
                    } else {
                        myLikedDrinks.forEach {
                            if (drinks != null) {
                                if (it.drinkID == drinks.idDrink) {
                                    likedDrink.key = it.key.toString()
                                    database.reference.child("Users").child(user.uid).child("LikedDrinks").child(it.key.toString()).removeValue()

                                    myLikedDrinks.remove(likedDrink)
                                    myLikedDrinks.removeIf { liked -> liked.key.equals(it.key.toString()) }

                                }
                            }
                        }
                    }
                }

            var liked = false
            myLikedDrinks?.forEach {
                if (drinks != null) {
                    if(drinks.idDrink == it.drinkID) {
                        liked = true
                    }
                }
            }

            toggleButton.isChecked = liked

            if (drinks != null) {
                Glide.with(itemView.context).load(drinks.strDrinkThumb).into(photo)
            }
            if (drinks != null) {
                title.text = drinks.strDrink
            }

            itemView.setOnClickListener{
                if (drinks != null) {
                    onDrinkClick.invoke(drinks)
                }
            }
        }
    }
    fun appendDrinks(drinks: List<Result?>) {

        this.drinks.addAll(drinks)
        notifyItemRangeInserted(this.drinks.size, drinks.size)
    }

}