package com.example.eus.ViewHome

import java.text.NumberFormat

class Util {
    companion object {
        fun convertToMoney(price: Double?): String {
            var numberFormat: NumberFormat = NumberFormat.getCurrencyInstance()
            var number : String = numberFormat.format(price)
            if (number.endsWith(".00")) {
                var pos = number.lastIndexOf(".00")
                if (pos != -1) {
                    number = number.substring(1,pos)
                }
            }
            return number
        }
    }
}