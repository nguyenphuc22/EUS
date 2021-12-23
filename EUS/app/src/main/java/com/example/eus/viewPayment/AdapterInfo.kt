package com.example.eus.viewPayment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.eus.ODT.Account
import com.example.eus.ODT.Product
import com.example.eus.ODT.ShipInfo
import com.example.eus.R
import com.example.eus.databinding.ItemChangeInfoBinding

class AdapterInfo(var onClickItem: OnClickItem) : RecyclerView.Adapter<AdapterInfo.InfoViewHolder>() {

    private var shipInfo = ArrayList<ShipInfo>()
    private lateinit var arrayBoolean: BooleanArray
    inner class InfoViewHolder(var binding: ItemChangeInfoBinding) : RecyclerView.ViewHolder(binding.root),
        PopupMenu.OnMenuItemClickListener {

        fun bind(shipInfo : ShipInfo) {
            binding.txtTitle.text = Util.formatTitleInfo(shipInfo.name, shipInfo.phone)
            binding.txtSubTitle.text = shipInfo.address

            binding.radio.isChecked = arrayBoolean.get(adapterPosition)

            binding.imageView4.setOnClickListener {
                var popupMenu = PopupMenu(binding.imageView4.context,binding.imageView4)
                popupMenu.inflate(R.menu.menu_crud)
                popupMenu.setOnMenuItemClickListener(this)
                popupMenu.show()
            }

            binding.radio.setOnClickListener {
                resetStateButtonRadio()
                arrayBoolean.set(adapterPosition,!arrayBoolean.get(adapterPosition))
                binding.radio.isChecked = arrayBoolean.get(adapterPosition)
                notifyDataSetChanged()
            }
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun onMenuItemClick(item: MenuItem?): Boolean {
            when(item?.itemId) {
                R.id.item_delete -> {
                    onClickItem.OnClickMenuDelete(shipInfo.get(adapterPosition))
                    shipInfo.removeAt(adapterPosition)
                    notifyDataSetChanged()
                    return true
                }
                R.id.item_update -> {
                    onClickItem.OnCLickMenuUpdate(shipInfo.get(adapterPosition))

                    return true
                }
            }
            return false
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

    fun setShipInfos(shipInfos : ArrayList<ShipInfo>) {
        this.shipInfo = shipInfos
        arrayBoolean = BooleanArray(shipInfo.size)
        if (arrayBoolean.size > 0) {
            arrayBoolean[0] = true
        }
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