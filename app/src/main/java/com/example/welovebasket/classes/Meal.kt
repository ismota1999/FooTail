package com.example.welovebasket.classes


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "mealInfo")
data class Meal(
    val dateModified: Any?,
    @PrimaryKey
    val idMeal: String,
    val strArea: String?,
    val strCategory: String?,
    val strCreativeCommonsConfirmed: Any?,
    val strDrinkAlternate: Any?,
    val strImageSource: Any?,
    val strIngredient1: String?,
    val strIngredient10: String?,
    val strIngredient11: String?,
    val strIngredient12: String?,
    val strIngredient13: String?,
    val strIngredient14: String?,
    val strIngredient15: String?,
    val strIngredient16: String?,
    val strIngredient17: String?,
    val strIngredient18: String?,
    val strIngredient19: String?,
    val strIngredient2: String?,
    val strIngredient20: String?,
    val strIngredient3: String?,
    val strIngredient4: String?,
    val strIngredient5: String?,
    val strIngredient6: String?,
    val strIngredient7: String?,
    val strIngredient8: String?,
    val strIngredient9: String?,
    val strInstructions: String?,
    val strMeal: String?,
    val strMealThumb: String?,
    val strMeasure1: String?,
    val strMeasure10: String?,
    val strMeasure11: String?,
    val strMeasure12: String?,
    val strMeasure13: String?,
    val strMeasure14: String?,
    val strMeasure15: String?,
    val strMeasure16: String?,
    val strMeasure17: String?,
    val strMeasure18: String?,
    val strMeasure19: String?,
    val strMeasure2: String?,
    val strMeasure20: String?,
    val strMeasure3: String?,
    val strMeasure4: String?,
    val strMeasure5: String?,
    val strMeasure6: String?,
    val strMeasure7: String?,
    val strMeasure8: String?,
    val strMeasure9: String?,
    val strSource: String?,
    val strTags: Any?,
    val strYoutube: String?
){
    fun getIngredients(): List<Ingredient> {

        val mutableList = mutableListOf<Ingredient>()


        strIngredient1?.let { mutableList.add(Ingredient(it, strMeasure1)) }
        strIngredient2?.let { mutableList.add(Ingredient(it, strMeasure2)) }
        strIngredient3?.let { mutableList.add(Ingredient(it, strMeasure3)) }
        strIngredient4?.let { mutableList.add(Ingredient(it, strMeasure4)) }
        strIngredient5?.let { mutableList.add(Ingredient(it, strMeasure5)) }
        strIngredient6?.let { mutableList.add(Ingredient(it, strMeasure6)) }
        strIngredient7?.let { mutableList.add(Ingredient(it, strMeasure7)) }
        strIngredient8?.let { mutableList.add(Ingredient(it, strMeasure8)) }
        strIngredient9?.let { mutableList.add(Ingredient(it, strMeasure9)) }
        strIngredient10?.let { mutableList.add(Ingredient(it, strMeasure10)) }
        strIngredient11?.let { mutableList.add(Ingredient(it, strMeasure11)) }
        strIngredient12?.let { mutableList.add(Ingredient(it, strMeasure12)) }
        strIngredient13?.let { mutableList.add(Ingredient(it, strMeasure13)) }
        strIngredient14?.let { mutableList.add(Ingredient(it, strMeasure14)) }
        strIngredient15?.let { mutableList.add(Ingredient(it, strMeasure15)) }
        return mutableList.toList()
    }
}
