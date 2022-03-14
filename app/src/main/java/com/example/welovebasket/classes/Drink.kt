package com.example.welovebasket.classes


import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.internal.AddLastDesc



data class Drink(
    val dateModified: String,
    val idDrink: String,
    val strAlcoholic: String,
    val strCategory: String,
    val strCreativeCommonsConfirmed: String,
    val strDrink: String,
    val strDrinkAlternate: Any,
    val strDrinkThumb: String,
    val strGlass: String,
    val strIBA: Any,
    val strImageAttribution: Any,
    val strImageSource: Any,
    val strIngredient1: String,
    val strIngredient10: String,
    val strIngredient11: String,
    val strIngredient12: String,
    val strIngredient13: String,
    val strIngredient14: String,
    val strIngredient15: String,
    val strIngredient2: String,
    val strIngredient3: String,
    val strIngredient4: String,
    val strIngredient5: String,
    val strIngredient6: String,
    val strIngredient7: String,
    val strIngredient8: String,
    val strIngredient9: String,
    val strInstructions: String,
    val strInstructionsDE: String,
    val strInstructionsES: String,
    val strInstructionsFR: String,
    val strInstructionsIT: String,
    @SerializedName("strInstructionsZH-HANS")
    val strInstructionsZHHANS: Any,
    @SerializedName("strInstructionsZH-HANT")
    val strInstructionsZHHANT: Any,
    val strMeasure1: String,
    val strMeasure10: Any,
    val strMeasure11: Any,
    val strMeasure12: Any,
    val strMeasure13: Any,
    val strMeasure14: Any,
    val strMeasure15: Any,
    val strMeasure2: String,
    val strMeasure3: String,
    val strMeasure4: String,
    val strMeasure5: Any,
    val strMeasure6: Any,
    val strMeasure7: Any,
    val strMeasure8: Any,
    val strMeasure9: Any,
    val strTags: Any,
    val strVideo: String
    )