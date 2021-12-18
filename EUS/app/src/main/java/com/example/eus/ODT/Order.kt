package com.example.eus.ODT

data class Order(
    var mId : String? = null,
    var mAddress : String? = null,
    var mPhone : String? = null,
    var mPayment : Payment? = null,
    var mName : String? = null,
    var mCart: Cart? = null
) {
    data class Builder (
        private var id : String? = null,
        private var address : String? = null,
        private var phone : String? = null,
        private var payment : Payment? = null,
        private var name : String? = null,
        private var cart: Cart? = null
            ) {
        fun addID(id : String) : Builder {
            this.id = id
            return this
        }
        fun addAddress(address: String) : Builder {
            this.address = address
            return this
        }
        fun addPayment(payment: Payment) : Builder {
            this.payment = payment
            return this
        }
        fun addCart(cart: Cart) : Builder {
            this.cart = cart
            return this
        }
        fun addName(name : String) : Builder {
            this.name = name
            return this
        }
        fun addPhone(phone : String) : Builder {
            this.phone = phone
            return this
        }
        fun build() : Order {
            return Order(id,address,phone,payment,name,cart)
        }

    }
}

