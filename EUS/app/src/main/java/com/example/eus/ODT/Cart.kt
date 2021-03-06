package com.example.eus.ODT

data class Cart(
    private var products: ArrayList<Product>
)
{

    fun calc() : Double {
        var price : Double = 0.0
        for (product in products) {
            price += product.mQuantity?.let { product.mPrice?.times(it) }!!
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

    fun getSize() : Int {
        return products.size
    }

    fun  delete(product: Product) {
        products.remove(product)
    }

    fun getCart(): ArrayList<Product>{
        return products
    }

    fun getProducts(): ArrayList<Product> {
        return this.products
    }

    fun removeItemASame() {
        for (product in this.products) {
            for (i in 0..this.products.size - 1) {
                if (product.mID!! == products[i].mID!!) {
                    products.removeAt(i)
                }
            }
        }
    }

    fun reset() {
        this.products = arrayListOf<Product>()
    }
}
