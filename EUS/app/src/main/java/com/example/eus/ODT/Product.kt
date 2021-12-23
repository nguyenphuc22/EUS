package com.example.eus.ODT

import android.os.Parcel
import android.os.Parcelable
import java.text.NumberFormat

data class Product(
    var mID : String? = null,
    var mImage: String? = null,
    var mPrice: Double? = null,
    var mTitle: String? = null,
    var mType : String? = null,
    var mDescription : String? = null,
    var mQuantity : Int? = null,
    var mName : String? = null,
    var mDetails : List<String>? = null,
    var mStock : Int? = null,
    ) : Parcelable
{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.createStringArrayList()
    ) {
    }

    data class Builder(
        private var mID : String? = null,
        private var mImage: String? = null,
        private var mPrice: Double? = null,
        private var mTitle: String? = null,
        private var mType : String? = null,
        private var mDescription : String? = null,
        private var mQuantity : Int? = null,
        private var mName : String? = null,
        private var mDetails : List<String>? = null,
        var mStock : Int? = null
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
        fun addStock( stock : Int) : Builder {
            this.mStock = stock
            return  this
        }
        fun build() : Product {
            return Product(mID,mImage,mPrice,mTitle,mType,mDescription,mQuantity,mName,mDetails,mStock)
        }
    }

    inner class Util() {
        fun getHeaderDetail(detail : String) : String {
            val result : List<String>
            result = detail.split(":").toList()
            if (result.size > 0) {
                return result.get(0)
            } else {
                return detail
            }
        }
        fun getValueDetail(detail : String) : String {
            val result : List<String>
            result = detail.split(":").toList()
            if (result.size > 0) {
                return result.get(1)
            } else {
                return detail
            }
        }
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

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(mID)
        parcel.writeString(mImage)
        parcel.writeValue(mPrice)
        parcel.writeString(mTitle)
        parcel.writeString(mType)
        parcel.writeString(mDescription)
        parcel.writeValue(mQuantity)
        parcel.writeString(mName)
        parcel.writeStringList(mDetails)    
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }

}