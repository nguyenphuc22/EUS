package com.example.eus.ViewCart

import com.example.eus.ODT.Product

interface OnClickCart {
    fun onClickPlus(product: Product)

    fun onClickMinus(product: Product)
}