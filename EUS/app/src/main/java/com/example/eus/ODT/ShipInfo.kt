package com.example.eus.ODT

data class ShipInfo(
    var name : String? = null,
    var phone : String? = null,
    var address : String? = null,
    var id : String? = null,
) {
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
}
