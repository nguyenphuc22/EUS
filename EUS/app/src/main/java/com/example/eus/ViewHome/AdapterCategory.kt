package com.example.eus.ViewHome

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eus.databinding.ItemButtonBinding

class AdapterCategory(var clickItemCategory: ClickItemCategory) : RecyclerView.Adapter<AdapterCategory.CategoryViewHolder>() {

    private var types : List<String> = ArrayList()

    inner class CategoryViewHolder(var binding : ItemButtonBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(type: String) {
            binding.btnCa.text = type

            binding.btnCa.setOnClickListener {
                clickItemCategory.onClickRadio(type)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ItemButtonBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
       holder.bind(types.get(position))
    }

    override fun getItemCount(): Int {
        return types.size
    }

    fun setData(list: List<String>) {
        types = list
        notifyDataSetChanged()
    }
}