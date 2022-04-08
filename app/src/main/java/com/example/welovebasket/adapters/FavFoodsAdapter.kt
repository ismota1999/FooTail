package com.example.welovebasket.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.welovebasket.classes.Drink
import com.example.welovebasket.classes.Meal
import com.example.welovebasket.databinding.FavDrinkCardBinding

class FavFoodsAdapter : RecyclerView.Adapter<FavFoodsAdapter.FavFoodsAdapterViewHolder>() {

    private var searchList:List<Meal> = ArrayList()
    private lateinit var onFavoriteClickListener: OnFavoriteClickListener

    inner class FavFoodsAdapterViewHolder(val binding : FavDrinkCardBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<Meal>(){
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffUtil)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavFoodsAdapterViewHolder {
        return FavFoodsAdapterViewHolder(
            FavDrinkCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    fun setOnFavoriteMealListener(onFavoriteClickListener: OnFavoriteClickListener) {
        this.onFavoriteClickListener = onFavoriteClickListener
    }

    override fun onBindViewHolder(holder: FavFoodsAdapterViewHolder, position: Int) {
        val meal = differ.currentList[position]
        holder.binding.apply {
            Glide.with(holder.itemView)
                .load(meal.strMealThumb)
                .into(holder.binding.imgFavMeal)
            holder.binding.tvFavMealName.text = meal.strMeal
            holder.itemView.setOnClickListener {
                onFavoriteClickListener.onFavoriteClick(differ.currentList[position])
            }
        }
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    interface OnFavoriteClickListener {
        fun onFavoriteClick(meal: Meal)
    }
}