package com.example.homeworkfour

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.Exclude

class LikedDrink {
    var key : String?
    var userID : String?
    var drinkID : String?
    var name : String?
//    var ingredientOne : String?
//    var ingredientTwo : String?
//    var ingredientThree : String?
//    var ingredientFour : String?
//    var ingredientFive : String?
//    var ingredientSix : String?
//    var ingredientSeven : String?
//
//    var measureOne : String?
//    var measureTwo : String?
//    var measureThree : String?
//    var measureFour : String?
//    var measureFive : String?
//    var measureSix : String?
//    var measureSeven : String?

    var thumbnail : String?

    @Exclude
    var result : Result?

    constructor() {
        key = null
        userID = null
        drinkID = null
        result = null
        name = null
//        ingredientOne = null
//        ingredientTwo = null
//        ingredientThree = null
//        ingredientFour = null
//        ingredientFive = null
//        ingredientSix = null
//        ingredientSeven = null
//
//        measureOne = null
//        measureTwo = null
//        measureThree = null
//        measureFour = null
//        measureFive = null
//        measureSix = null
//        measureSeven = null

        thumbnail = null


    }
    constructor(key: String?, userID: String?, drinkID: String?, name: String?, thumbnail: String? ) {
        this.key = key
        this.userID = userID
        this.drinkID = drinkID
        this.name = name

//
//        this.ingredientOne = ingredientOne
//        this.ingredientTwo = ingredientTwo
//        this.ingredientThree = ingredientThree
//        this.ingredientFour = ingredientFour
//        this.ingredientFive = ingredientFive
//        this.ingredientSix = ingredientSix
//        this.ingredientSeven = ingredientSeven
//
//        this.measureOne = measureOne
//        this.measureTwo = measureTwo
//        this.measureThree = measureThree
//        this.measureFour = measureFour
//        this.measureFive = measureFive
//        this.measureSix = measureSix
//        this.measureSeven = measureSeven

        this.thumbnail = thumbnail



        result = null

    }


}