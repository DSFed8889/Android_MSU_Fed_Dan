package com.example.shopbasket

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ItemCartAdapter(private val context: Context, private val dataSet: List<ItemCart>): RecyclerView.Adapter<ItemCartAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvProductPrice: TextView
        val tvProductCount: TextView
        val tvProductName: TextView
        val imageView: ImageView

        init {
            tvProductPrice = view.findViewById(R.id.productPrice)
            tvProductCount = view.findViewById(R.id.productCount)
            tvProductName = view.findViewById(R.id.productName)
            imageView = view.findViewById(R.id.imageURL)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            tvProductPrice.text = String.format("%.2f", dataSet[position].price)
            tvProductCount.text = generateStockText(dataSet[position].count)
            tvProductName.text = dataSet[position].productName;
            Glide.with(context)
                .load(dataSet[position].imageURL)
                .centerCrop()
                .into(imageView)
        }
    }

    private fun generateStockText(count: Int): String =
        if (count > 10) "in stock"
        else if (count == 0) "not in stock"
        else "only $count left in stock"

    override fun getItemCount(): Int = dataSet.size
}