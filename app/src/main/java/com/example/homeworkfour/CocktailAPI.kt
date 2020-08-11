package com.example.homeworkfour
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailAPI {

        @GET("randomselection.php")
        fun randomDrinks(
            @Query("s") searchTerms: String): Call<Drinks>
        //fun getRandom(@Query("s") searchTerms: String): Call<Drinks>
        @GET("search.php")
        fun searchDrinksByName(
            @Query("s") searchTerms: String): Call<Drinks>
        @GET("lookup.php")
        fun searchDrinksById(
            @Query("s") searchTerms: String): Call<Drinks>



}

// test searches
// https://thecocktaildb.com/api/json/v1/1/lookup.php?i=13058
// https://thecocktaildb.com/api/json/v2/9973533/search.php?s=jam_donut
// https://www.thecocktaildb.com/api/json/v1/1/randomselection.php
