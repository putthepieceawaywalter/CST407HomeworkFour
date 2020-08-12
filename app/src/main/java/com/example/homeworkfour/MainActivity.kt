package com.example.homeworkfour

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), View.OnClickListener{


    private var drinks: MutableList<Result?> = ArrayList()
    val newRequest = ServiceBuilder.buildService()

    val likedDrinks: MutableList<LikedDrink> = ArrayList<LikedDrink>()


    private var favoriteDrinks: MutableList<LikedDrink> = mutableListOf()
    var favoriteDrinkResults :MutableList<Result?> = mutableListOf()

    private var drinksAdapter: DrinksAdapter = DrinksAdapter(drinks, ::drinkDetails, favoriteDrinks)
    private var favoriteAdapter: FavoriteDrinkAdapter = FavoriteDrinkAdapter(likedDrinks, ::drinkDetails)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        btn_scroll_drinks_button.setOnClickListener(this)
        btn_view_favorite_drinks.setOnClickListener(this)
        btn_search.setOnClickListener(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = drinksAdapter
    }

    override fun onClick(button: View?) {
        when(button?.id) {
            R.id.btn_scroll_drinks_button -> {
                scrollDrinks()

            }
            R.id.btn_view_favorite_drinks -> {
                favorites()
            }
            R.id.btn_search -> {
                search()
                hideKeyboard()
            }
        }
    }


    private fun AppCompatActivity.hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

    }
    private fun drinkDetails(drink : Result) {
        val intent = Intent(this, DrinkDetails::class.java)
        intent.putExtra("DRINK", drink)
        startActivity(intent)


    }

//    private fun favoriteDetails(likedDrink : LikedDrink) {
    // Hey Lucas I know you don't like to do's in comments but you'll have to deal with this one!
//        val intent = Intent(this, LikedDrink::class.java)
//        intent.putExtra("LIKEDDRINK", likedDrink)
//        startActivity()
//    }
    private fun onSuccess(drinks: List<Result?>) {

        // added mutable list of as third parameter so the red line would go away, probably not correct
        getLikedDrinks()
        recyclerView.adapter = drinks?.toMutableList()?.let { DrinksAdapter(it, ::drinkDetails, likedDrinks) }
        txtViewDescription.visibility = View.GONE
        txtViewTitle.visibility = View.GONE
        if (drinks != null) {
            drinksAdapter.appendDrinks(drinks)
        }

        else {
            onError()
        }

    }

    private fun onError() {
        Toast.makeText(this, "No results found", Toast.LENGTH_SHORT).show()
    }

    private fun onFavoriteDrinkSuccess() {
        getLikedDrinks()
        recyclerView.adapter = favoriteAdapter
        recyclerView.adapter = likedDrinks?.toMutableList()?.let { FavoriteDrinkAdapter(it, ::drinkDetails)}
        if (likedDrinks != null)( {
            favoriteAdapter.appendDrinks(likedDrinks)


        })
    }

    private fun onFavoriteDrinkError() {
        Toast.makeText(this, "No results found", Toast.LENGTH_SHORT).show()
    }
    private fun scrollDrinks() {

        var searchTerms = "Long Island"
        val newCall = newRequest.randomDrinks(searchTerms)

        newCall.enqueue(object : Callback<Drinks> {
            override fun onFailure(call: Call<Drinks>, t: Throwable) {

            }

            override fun onResponse(call: Call<Drinks>, response: Response<Drinks>) {
                if (response.isSuccessful) {
                    if (response.body()?.drinks?.size != 0) {

                        response.body()?.drinks?.let { onSuccess(it) }


                    } else {
                        onError()
                    }
                }
                else {
                    onError()
                }
            }

        })
    }
    private fun favorites() {

        val user = FirebaseAuth.getInstance().currentUser
        val database = FirebaseDatabase.getInstance()
        lateinit var drinkIdReference: DatabaseReference
        drinkIdReference = Firebase.database.reference.child("Users").child(user!!.uid).child("LikedDrinks")

        val likedDrinksListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                favoriteDrinks.clear()
                likedDrinks.clear()

                for (drink in dataSnapshot.children) {
                   val likedDrink: LikedDrink? = drink.getValue<LikedDrink>()
                    if (likedDrink != null) {
                        likedDrinks.add(likedDrink)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        }
        drinkIdReference.addValueEventListener(likedDrinksListener)
        onFavoriteDrinkSuccess()
    }

    private fun search() {
        var searchTerms = edit_txt_drink_search.text.toString()
        var searchTermsValidity: Boolean = false

        if (searchTerms.isEmpty()) {
            onError()
        }
        else {


            val newCall = newRequest.searchDrinksByName(searchTerms)

            newCall.enqueue(object : Callback<Drinks> {
                override fun onFailure(call: Call<Drinks>, t: Throwable) {
                }

                override fun onResponse(call: Call<Drinks>, response: Response<Drinks>) {
                    if (response.isSuccessful) {
                        if (response.body()?.drinks?.size != null) {

                            response.body()?.drinks?.let { onSuccess(it) }

                        } else {
                            onError()
                        }
                    } else {
                        onError()
                    }
                }

            })
        }
        edit_txt_drink_search.text.clear()
    }

    private fun getLikedDrinks() {
        // updateLikedDrinks would be a more fitting name because this doesn't return the current liked drinks, it updates the reference

        val user = FirebaseAuth.getInstance().currentUser
        val database = FirebaseDatabase.getInstance()
        lateinit var drinkIdReference: DatabaseReference
        drinkIdReference = Firebase.database.reference.child("Users").child(user!!.uid).child("LikedDrinks")

        val likedDrinksListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                favoriteDrinks.clear()
                likedDrinks.clear()

                for (drink in dataSnapshot.children) {
                    val likedDrink: LikedDrink? = drink.getValue<LikedDrink>()
                    if (likedDrink != null) {
                        likedDrinks.add(likedDrink)
                    }
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        }
        drinkIdReference.addValueEventListener(likedDrinksListener)
    }
}