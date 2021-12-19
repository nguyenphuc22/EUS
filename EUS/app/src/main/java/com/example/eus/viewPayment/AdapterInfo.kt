package com.example.eus.viewPayment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eus.ODT.Account
import com.example.eus.databinding.ItemChangeInfoBinding

class AdapterInfo : RecyclerView.Adapter<AdapterInfo.InfoViewHolder>() {

    private lateinit var accounts : ArrayList<Account>

    class InfoViewHolder(var binding: ItemChangeInfoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(account: Account) {
            Log.i("AdapterInfo",account.toString())
            binding.txtTitle.text = Util.formatTitleInfo(account.mName, account.mPhone)
            binding.txtSubTitle.text = account.mAddress
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        return InfoViewHolder(ItemChangeInfoBinding.inflate(
            LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        holder.bind(accounts.get(position))
    }

    override fun getItemCount(): Int {
        return accounts.size
    }

    fun setAccounts(accounts : ArrayList<Account>) {
        this.accounts = accounts
    }

}