package com.example.eus.Cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey
    var mID : String,
    @ColumnInfo
    var mImage: String? = null,
    @ColumnInfo
    var mPrice: Double? = null,
    @ColumnInfo
    var mTitle: String? = null,
    @ColumnInfo
    var mType : String? = null,
    @ColumnInfo
    var mDescription : String? = null,
    @ColumnInfo
    var mQuantity : Int? = null,
    @ColumnInfo
    var mName : String? = null,
) {

    data class Builder(
        var mID : String? = null,
        var mImage: String? = null,
        var mPrice: Double? = null,
        var mTitle: String? = null,
        var mType: String? = null,
        var mDescription: String? = null,
        var mQuantity: Int? = null,
        var mName: String? = null,
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

        fun build() : Product {
            if (this.mID != null) {
                return Product(mID!!, mImage, mPrice, mTitle, mType, mDescription, mQuantity, mName)
            }
            return Product(getRandomString(9), mImage, mPrice, mTitle, mType, mDescription, mQuantity, mName)
        }



        private fun getRandomString(length: Int) : String {
            val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9')

            return List(length) { charset.random() }
                .joinToString("")
        }
    }



    override fun toString(): String {
        return "Product(mID='$mID', mPrice=$mPrice, mTitle=$mTitle, mType=$mType, mDescription=$mDescription, mQuantity=$mQuantity, mName=$mName)"
    }
}
