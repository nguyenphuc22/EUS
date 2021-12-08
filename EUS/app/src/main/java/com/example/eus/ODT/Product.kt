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
        fun addImage(image : String) : Builder {
            this.mImage = image
            return this
        }
        fun addPrice(price : Double) : Builder {
            this.mPrice = price
            return this
        }
        fun addTitle(title : String) : Builder {
            this.mTitle = title
            return this
        }
        fun build() : Product {
            return Product(mImage,mPrice,mTitle)
        }
    }
}