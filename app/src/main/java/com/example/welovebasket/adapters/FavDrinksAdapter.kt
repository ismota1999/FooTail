package com.example.welovebasket.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.welovebasket.classes.Drink
import com.example.welovebasket.databinding.FavDrinkCardBinding

class FavDrinksAdapter : RecyclerView.Adapter<FavDrinksAdapter.FavDrinksAdapterViewHolder>() {
    private var favoriteDrinks: List<Drink> = ArrayList()
    private lateinit var onFavoriteClickListener: OnFavoriteClickListener

    inner class FavDrinksAdapterViewHolder(val binding : FavDrinkCardBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<Drink>(){
        override fun areItemsTheSame(oldItem: Drink, newItem: Drink): Boolean {
            return oldItem.idDrink == newItem.idDrink
        }

        override fun areContentsTheSame(oldItem: Drink, newItem: Drink): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavDrinksAdapterViewHolder {
        return FavDrinksAdapterViewHolder(
            FavDrinkCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    fun getDrinkByPosition(position: Int):Drink{
        return differ.currentList[position]
    }

    fun setOnFavoriteMealClickListener(onFavoriteClickListener: OnFavoriteClickListener) {
        this.onFavoriteClickListener = onFavoriteClickListener
    }


    override fun onBindViewHolder(holder: FavDrinksAdapterViewHolder, position: Int) {
        val drink = differ.currentList[position]
        holder.binding.apply {
            Glide.with(holder.itemView)
                .load(drink.strDrinkThumb)
                .into(holder.binding.imgFavMeal)
        holder.binding.tvFavMealName.text = drink.strDrink
        holder.itemView.setOnClickListener {
            onFavoriteClickListener.onFavoriteClick(differ.currentList[position])
        }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }



    interface OnFavoriteClickListener {
        fun onFavoriteClick(drink: Drink)
    }

}