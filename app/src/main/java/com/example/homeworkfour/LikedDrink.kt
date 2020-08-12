package com.example.homeworkfour

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.Exclude

class LikedDrink {
    var key : String?
    var userID : String?
    var drinkID : String?
    var name : String?


    var thumbnail : String?

    @Exclude
    var result : Result?

    constructor() {
        key = null
        userID = null
        drinkID = null
        result = null
        name = null

        thumbnail = null


    }
    constructor(key: String?, userID: String?, drinkID: String?, name: String?, thumbnail: String? ) {
        this.key = key
        this.userID = userID
        this.drinkID = drinkID
        this.name = name


        this.thumbnail = thumbnail



        result = null

    }

}