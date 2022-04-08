package com.example.welovebasket.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.welovebasket.classes.Drink
import com.example.welovebasket.classes.Meal
import com.example.welovebasket.databinding.FavDrinkCardBinding

class CocktailSearcherAdapter : RecyclerView.Adapter<CocktailSearcherAdapter.CocktailViewHolder>() {

    private var drinkList: List<Drink> = ArrayList()
    private lateinit var setOnDrinkListener: SetOnDrinkListener

    fun setCategoryList(drinkList: List<Drink>) {
        this.drinkList = drinkList
        notifyDataSetChanged()
    }

    fun setOnMealClickListener(setOnDrinkListener: SetOnDrinkListener) {
        this.setOnDrinkListener = setOnDrinkListener
    }

    class CocktailViewHolder(val binding: FavDrinkCardBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        return CocktailViewHolder(FavDrinkCardBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        holder.binding.apply {
            Glide.with(holder.itemView)
                .load(drinkList[position].strDrinkThumb)
                .into(holder.binding.imgFavMeal)
            holder.binding.tvFavMealName.text = drinkList[position].strDrink
        }
    }

    override fun getItemCount(): Int {
        return drinkList.size
    }
}

interface SetOnDrinkListener {
    fun setOnClickListener(drink: Drink)
}