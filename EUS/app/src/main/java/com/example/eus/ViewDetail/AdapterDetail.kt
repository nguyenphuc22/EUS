package com.example.eus.ViewDetail

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eus.ODT.Product
import com.example.eus.databinding.ItemDetailProductBinding

class AdapterDetail(private var mDetails : List<String>) : RecyclerView.Adapter<AdapterDetail.DetailViewHolder>() {


    class DetailViewHolder(var binding: ItemDetailProductBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(details: String) {
            val header = Product().Util().getHeaderDetail(details)
            val value  = Product().Util().getValueDetail(details)
            binding.txtHeader.text = header
            binding.txtValue.text = value

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(
            ItemDetailProductBinding.inflate(
                LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(mDetails.get(position))
    }

    override fun getItemCount(): Int {
        return mDetails.size
    }

}