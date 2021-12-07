package com.example.eus.ODT

data class Product(
    var mImage: String? = null,
    var mPrice: Double? = null,
    var mTitle: String? = null
    )
{
    data class Builder(
        private var mImage : String? = null,
        private var mPrice : Double? = null,
        private var mTitle : String? = null
    ) {
        fun addImage(image : String) = apply { this.mImage = image }
        fun addPrice(price : Double) = apply { this.mPrice = price }
        fun addTitle(title : String) = apply { this.mTitle = title }
        fun build() = Product(mImage,mPrice,mTitle)
    }
}