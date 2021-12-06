package com.example.eus.ViewHome

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eus.databinding.ItemButtonBinding

class AdapterCategory : RecyclerView.Adapter<AdapterCategory.CategoryViewHolder>() {

    private lateinit var types : List<String>

    init {
        var list = ArrayList<String>()
        for (i in 0..9) {
            list.add("Tivi")
        }
        types = list
    }

    class CategoryViewHolder(var binding : ItemButtonBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(type: String) {
            binding.btnCa.text = type
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

}