package com.example.eus.ODT

data class Product(
    var mID : String? = null,
    var mImage: String? = null,
    var mPrice: Double? = null,
    var mTitle: String? = null,
    var mType : String? = null,
    var mDescription : String? = null,
    var mQuantity : Int? = null,
    var mName : String? = null,
    var mDetails : List<String>? = null
    )
{
    data class Builder(
        private var mID : String? = null,
        private var mImage: String? = null,
        private var mPrice: Double? = null,
        private var mTitle: String? = null,
        private var mType : String? = null,
        private var mDescription : String? = null,
        private var mQuantity : Int? = null,
        private var mName : String? = null,
        private var mDetails : List<String>? = null
    ) {
        fun addID( id : String) : Builder {
            this.mID = id
            return this
        }
        fun addImage(url : String) : Builder {
            this.mImage = url
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
        fun addType(type : String) : Builder {
            this.mType = type
            return this
        }
        fun addDescription(des : String) : Builder {
            this.mDescription = des
            return this
        }
        fun addQuantity(quantity : Int) : Builder {
            this.mQuantity = quantity
            return this
        }
        fun addName(name : String) : Builder {
            this.mName = name
            return this
        }
        fun addDetails( details : List<String>) : Builder {
            this.mDetails = details
            return  this
        }
        fun build() : Product {
            return Product(mID,mImage,mPrice,mTitle,mType,mDescription,mQuantity,mName,mDetails)
        }
    }
}