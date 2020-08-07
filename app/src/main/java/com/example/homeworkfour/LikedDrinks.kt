package com.example.homeworkfour

class LikedDrinks {
    var key : String?
    var userID : String?
    var drinkID : String?

    constructor() {
        key = null
        userID = null
        drinkID = null
    }
    constructor(key: String?, userID: String?, drinkID: String?) {
        this.key = key
        this.userID = userID
        this.drinkID = drinkID
    }
}