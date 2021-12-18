package com.example.eus.ODT

class COD : Payment{

    override fun getType(): String {
        return "Thanh Toán COD"
    }

    override fun paid(): Boolean {
        TODO("Not yet implemented")
    }

}