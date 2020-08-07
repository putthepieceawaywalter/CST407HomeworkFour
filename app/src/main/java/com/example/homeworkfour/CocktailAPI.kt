package com.example.homeworkfour
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailAPI {

     @GET("randomselection.php")
        fun searchDrinks(
            @Query("s") searchTerms: String)

                : Call<Drinks>
        //fun getRandom(@Query("s") searchTerms: String): Call<Drinks>
    fun getRandomDrinks(
            @Query("s") searchTerms: String): Call<Drinks>

}
