package com.example.eus.ODT

import android.os.Parcel
import android.os.Parcelable

data class ShipInfo(
    var name : String? = null,
    var phone : String? = null,
    var address : String? = null,
    var id : String? = null,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {

    }
    fun toMap(): Map<String, Any?>{
        return mapOf(
            "name" to name,
            "address" to address,
            "id" to id,
            "phone" to phone
        )
    }

    data class Builder(
        private var name : String? = null,
        private var phone : String? = null,
        private var address : String? = null,
        private var id : String? = null,
    ) {
        fun addName(name : String) : Builder {
            this.name = name;
            return this
        }
        fun addPhone(phone : String) : Builder {
            this.phone = phone;
            return this
        }
        fun addAddress(address : String) : Builder {
            this.address = address;
            return this
        }
        fun addId(id : String) : Builder {
            this.id = id;
            return this
        }
        fun build() : ShipInfo {
            return ShipInfo(name,phone,address,id)
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(phone)
        parcel.writeString(address)
        parcel.writeString(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ShipInfo> {
        override fun createFromParcel(parcel: Parcel): ShipInfo {
            return ShipInfo(parcel)
        }

        override fun newArray(size: Int): Array<ShipInfo?> {
            return arrayOfNulls(size)
        }
    }
}
