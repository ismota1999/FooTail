package com.example.welovebasket.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.welovebasket.classes.Meal
import com.example.welovebasket.databinding.FavDrinkCardBinding

class MealSearchAdapter : RecyclerView.Adapter<MealSearchAdapter.MealViewHolder>() {

    private var mealList: List<Meal> = ArrayList()
    private lateinit var setOnMealClickListener: SetOnMealClickListener

    fun setCategoryList(mealList: List<Meal>) {
        this.mealList = mealList
        notifyDataSetChanged()
    }


    class MealViewHolder(val binding: FavDrinkCardBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        return MealViewHolder(FavDrinkCardBinding.inflate(LayoutInflater.from(parent.context)))
    }

    fun setOnMealClickListener(setOnMealClickListener: SetOnMealClickListener) {
        this.setOnMealClickListener = setOnMealClickListener
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.binding.apply {
            Glide.with(holder.itemView)
                .load(mealList[position].strMealThumb)
                .into(holder.binding.imgFavMeal)
            holder.binding.tvFavMealName.text = mealList[position].strMeal
        }
    }

    override fun getItemCount(): Int {
        return mealList.size
    }


    interface SetOnMealClickListener {
        fun setOnClickListener(meal: Meal)
    }

}