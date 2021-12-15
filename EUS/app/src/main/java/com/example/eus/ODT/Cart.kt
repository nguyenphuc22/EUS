package com.example.eus.ODT

data class Cart(
    private var products : ArrayList<Product>
) {
    fun calc() : Double {
        var price : Double = 0.0
        for (product in products) {
            price += product.mPrice!!
        }
        return price
    }
    fun add(product: Product) {
        products.add(product)
    }
    fun delete(index : Int) {
        products.remove(getProduct(index))
    }
    fun getProduct(index: Int) : Product? {
        if (index > products.size) {
            return null
        }
        return products.get(index)
    }

}
