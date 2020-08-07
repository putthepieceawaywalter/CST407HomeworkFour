package com.example.homeworkfour

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private val cocktailAPI:CocktailAPI
    init {

        val retrofit = Retrofit.Builder()

            .baseUrl("https://www.thecocktaildb.com/api/json/v2/9973533/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        cocktailAPI = retrofit.create(CocktailAPI::class.java)
    }


    fun buildService(): CocktailAPI{
        return cocktailAPI
    }
}