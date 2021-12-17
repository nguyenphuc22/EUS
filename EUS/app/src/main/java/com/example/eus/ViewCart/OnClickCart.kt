package com.example.eus.ViewCart

import com.example.eus.ODT.Product

interface OnClickCart {
    fun onClickPlus(product: Product)
    fun onUpdatePrice(price: Double)
    fun onClickMinus(product: Product)
}