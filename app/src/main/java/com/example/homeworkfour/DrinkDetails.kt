package com.example.homeworkfour
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_drink_details.*


class DrinkDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_details)

        val drink : Result = intent.getSerializableExtra("DRINK") as Result

        drink_name.text = drink.strDrink
        drink_instructions.text = drink.strInstructions
        ingredient_one.text = drink.strIngredient1
        ingredient_two.text = drink.strIngredient2
        ingredient_three.text = drink.strIngredient3
        ingredient_four.text = drink.strIngredient4
        ingredient_five.text = drink.strIngredient5
        ingredient_six.text = drink.strIngredient6
        ingredient_seven.text = drink.strIngredient7

        measure_one.text = drink.strMeasure1
        measure_two.text = drink.strMeasure2
        measure_three.text = drink.strMeasure3
        measure_four.text = drink.strMeasure4
        measure_five.text = drink.strMeasure5
        measure_six.text = drink.strMeasure6
        measure_seven.text = drink.strMeasure7
    }
}