package com.example.eus.ViewCart

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eus.ODT.Cart
import com.example.eus.ODT.Product
import com.example.eus.R
import com.example.eus.databinding.ItemCartBinding

class AdapterCart(var onClickCart: OnClickCart) : RecyclerView.Adapter<AdapterCart.CartViewHolder>() {

    private var mCart = Cart(ArrayList<Product>())

    inner class CartViewHolder(var binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged")
        fun bind(product: Product?) {
            Glide.with(binding.root)
                .load(product?.mImage)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.img)
            binding.txtName.text = product?.mTitle
            binding.textPrice.text = product?.Util()?.convertToMoney(product?.mPrice)
            binding.txtNumber.text = product?.mQuantity.toString()

            binding.txtPlus.setOnClickListener {
                product?.let { it1 -> onClickCart.onClickPlus(product = it1) }
                Log.i("AdapterCart",product?.mQuantity.toString())
                onClickCart.onUpdatePrice(mCart.calc())
                notifyDataSetChanged()
            }

            binding.txtMinus.setOnClickListener {
                product?.let { it1 -> onClickCart.onClickMinus(product = it1) }
                Log.i("AdapterCart",product?.mQuantity.toString())
                onClickCart.onUpdatePrice(mCart.calc())
                if (product?.mQuantity!! < 1) {
                    mCart.delete(product)
                }
                notifyDataSetChanged()
            }

            binding.txtDelete.setOnClickListener {
                if (product != null) {
                    mCart.delete(product)
                }
                onClickCart.onUpdatePrice(mCart.calc())
                notifyDataSetChanged()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(
            ItemCartBinding.inflate(
                LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(mCart.getProduct(position))
    }

    override fun getItemCount(): Int {
        return mCart.getSize()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(cart: Cart) {
        mCart.reset()
        mCart = cart
        onClickCart.onUpdatePrice(mCart.calc())
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addProduct(product: Product) {
        mCart.add(product)
        onClickCart.onUpdatePrice(mCart.calc())
        notifyDataSetChanged()
    }

}