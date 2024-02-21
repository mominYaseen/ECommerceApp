package com.example.ecommerceapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide

class ProductAdapter(
    private val listOfProducts : List<Product>,
    private val context:Context
) :RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){
    class ProductViewHolder(itemView: View):ViewHolder(itemView){
        val productName : TextView = itemView.findViewById(R.id.rv_name)
        val productPrice : TextView = itemView.findViewById(R.id.rv_price)
        val productDes : TextView = itemView.findViewById(R.id.rv_des)
        val productRating : TextView = itemView.findViewById(R.id.rv_rating)
        val productImgPreview : ImageView = itemView.findViewById(R.id.rv_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item,parent,false)
        return ProductViewHolder(view)

    }

    override fun getItemCount(): Int {
        return listOfProducts.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentProduct = listOfProducts[position]
        holder.productName.text = currentProduct.productName
        holder.productPrice.text = currentProduct.productPrice
        holder.productDes.text = currentProduct.productDis
        holder.productRating.text = currentProduct.productRating

        Glide.with(context)
            .load(currentProduct.productImg)
            .into(holder.productImgPreview)

    }
}