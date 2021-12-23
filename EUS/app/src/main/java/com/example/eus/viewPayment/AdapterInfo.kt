package com.example.eus.viewPayment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.eus.ODT.Account
import com.example.eus.ODT.Product
import com.example.eus.ODT.ShipInfo
import com.example.eus.databinding.ItemChangeInfoBinding

class AdapterInfo : RecyclerView.Adapter<AdapterInfo.InfoViewHolder>() {

    private lateinit var shipInfo : ArrayList<ShipInfo>
    private lateinit var arrayBoolean: BooleanArray
    inner class InfoViewHolder(var binding: ItemChangeInfoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(shipInfo : ShipInfo) {
            binding.txtTitle.text = Util.formatTitleInfo(shipInfo.name, shipInfo.phone)
            binding.txtSubTitle.text = shipInfo.address

            binding.radio.isChecked = arrayBoolean.get(adapterPosition)

            binding.radio.setOnClickListener {
                resetStateButtonRadio()
                arrayBoolean.set(adapterPosition,!arrayBoolean.get(adapterPosition))
                binding.radio.isChecked = arrayBoolean.get(adapterPosition)
                notifyDataSetChanged()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        return InfoViewHolder(ItemChangeInfoBinding.inflate(
            LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        holder.bind(shipInfo.get(position))
    }

    override fun getItemCount(): Int {
        return shipInfo.size
    }

    fun setAccounts(shipInfos : ArrayList<ShipInfo>) {
        this.shipInfo = shipInfos
        arrayBoolean = BooleanArray(shipInfo.size)
        notifyDataSetChanged()
    }

    private fun resetStateButtonRadio() {
        if (arrayBoolean.count { it } >= 0) {
            for (i in 0..arrayBoolean.size - 1) {
                arrayBoolean.set(i,false)
            }
        }
    }

    fun getInfo(): ShipInfo? {
        var shipInfo : ShipInfo? = null
        for (i in 0..arrayBoolean.size - 1) {
            if (arrayBoolean.get(i) == true) {
                shipInfo = this.shipInfo.get(i)
            }
        }
        return shipInfo

    }

}