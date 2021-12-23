package com.example.eus.ODT

data class Order(
    var mId : String? = null,
    var shipInfo: ShipInfo? = null,
    var mPayment : Payment? = null,
    var mCart: Cart? = null
) {
    data class Builder (
        private var id : String? = null,
        private var shipInfo: ShipInfo? = null,
        private var payment : Payment? = null,
        private var cart: Cart? = null
            ) {
        fun addID(id : String) : Builder {
            this.id = id
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
        fun addShipInfo(shipInfo: ShipInfo?) : Builder {
            this.shipInfo = shipInfo
            return this
        }
        fun build() : Order {
            return Order(id,shipInfo,payment,cart)
        }

    }
}

