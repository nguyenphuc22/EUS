package com.example.eus.ViewHome

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eus.ODT.Product
import com.example.eus.R
import com.example.eus.databinding.ItemProductBinding

class AdapterProduct : RecyclerView.Adapter<AdapterProduct.ProductViewHolder>() {

    private var products : ArrayList<Product> = ArrayList()
    private var onClickItemProduct : OnClickItemProduct? = null

    inner class ProductViewHolder(var binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.txtTitle.text = product.mTitle
            binding.txtPrice.text = product.Util().convertToMoney(product.mPrice)
            Glide.with(binding.root)
                .load(product.mImage)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.imageView2)
            binding.root.setOnClickListener {
                onClickItemProduct?.onCLickProduct(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemProductBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products.get(position))
    }

    override fun getItemCount(): Int {
        return products.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setProduct(list : List<Product>) {
        this.products = ArrayList()
        this.products = list as ArrayList<Product>
        notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun addProduct(product: Product) {
        this.products.add(product)
        notifyDataSetChanged()
    }
    fun addOnClickItem(onClickItemProduct: OnClickItemProduct) {
        this.onClickItemProduct = onClickItemProduct
    }
}