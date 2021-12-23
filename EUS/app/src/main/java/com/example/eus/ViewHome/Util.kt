package com.example.eus.ViewHome

import android.util.Log
import com.example.eus.ODT.*
import androidx.lifecycle.MutableLiveData as MutableLiveData

class Util {
    companion object {

        fun fakeCategory() : MutableLiveData<List<String>> {
            var mutableLiveData : MutableLiveData<List<String>> = MutableLiveData()
            var list = ArrayList<String>()

            list.add("Tivi")
            list.add("Tủ Lạnh")
            list.add("Bàn Thờ")
            list.add("Điện Thoại")
            list.add("Laptop")
            list.add("Cpu")
            list.add("Gpu")
            list.add("Máy ")
            list.add("Tablet")
            list.add("Bao Cao Su")

            mutableLiveData.value = list
            return mutableLiveData
        }

        fun fakeData() : MutableLiveData<List<Product>> {
            val mutableLiveData : MutableLiveData<List<Product>> = MutableLiveData()
            val list = ArrayList<Product>()
            val product = Product.Builder()
                .addImage("https://upload.wikimedia.org/wikipedia/commons/0/0c/E-girl.png")
                .addPrice(44000000.0)
                .addTitle("Điện thoại")
                .build()
            list.add(product)
            list.add(Product.Builder()
                .addImage("https://vnn-imgs-a1.vgcloud.vn/icdn.dantri.com.vn/2021/05/08/kimoanh-851-1620472406599.jpeg")
                .addPrice(23000000.0)
                .addTitle("Điện thoại đẹp")
                .build())
            list.add(Product.Builder()
                .addImage("https://media.istockphoto.com/photos/lonly-young-woman-by-the-sea-picture-id840501418?b=1&k=20&m=840501418&s=170667a&w=0&h=2Smu2ybUDuZB120uKmQeEtegDXCTXHSLd3yL9GouwuQ=")
                .addPrice(42000000.0)
                .addTitle("Điện thoại đẹp")
                .build())
            list.add(Product.Builder()
                .addImage("https://icdn.dantri.com.vn/thumb_w/640/2021/02/27/diem-danh-7-guong-mat-hot-girl-xinh-dep-noi-bat-trong-thang-2-docx-1614441453135.jpeg")
                .addPrice(55000000.0)
                .addTitle("Điện thoại đẹp")
                .build())
            list.add(Product.Builder()
                .addImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTk3O6wPUnvDOyGr-8Wbd6-lihTBhXQNfeCyg&usqp=CAU")
                .addPrice(20000000.0)
                .addTitle("Điện thoại đẹp")
                .build())
            mutableLiveData.value = list
            return mutableLiveData
        }

        fun fakeProduct(): Product {
            var list = ArrayList<String>()
            for (i in 0..20) {
                list.add("Header:value")
            }
            return Product.Builder()
                .addImage("https://upload.wikimedia.org/wikipedia/commons/0/0c/E-girl.png")
                .addDetails(list)
                .addDescription("Điện thoại ngon pro vip 18+")
                .addPrice(44000000.0)
                .addTitle("Điện thoại")
                .build()
        }

        fun fakeCart() : MutableLiveData<Cart> {
            val list = ArrayList<Product>()
            val product = Product.Builder()
                .addImage("https://upload.wikimedia.org/wikipedia/commons/0/0c/E-girl.png")
                .addPrice(44000000.0)
                .addTitle("Điện thoại")
                .addQuantity(1)
                .build()
            list.add(product)
            list.add(Product.Builder()
                .addImage("https://vnn-imgs-a1.vgcloud.vn/icdn.dantri.com.vn/2021/05/08/kimoanh-851-1620472406599.jpeg")
                .addPrice(23000000.0)
                .addTitle("Điện thoại đẹp")
                .addQuantity(1)
                .build())
            list.add(Product.Builder()
                .addImage("https://media.istockphoto.com/photos/lonly-young-woman-by-the-sea-picture-id840501418?b=1&k=20&m=840501418&s=170667a&w=0&h=2Smu2ybUDuZB120uKmQeEtegDXCTXHSLd3yL9GouwuQ=")
                .addPrice(42000000.0)
                .addTitle("Điện thoại đẹp")
                .addQuantity(1)
                .build())
            list.add(Product.Builder()
                .addImage("https://icdn.dantri.com.vn/thumb_w/640/2021/02/27/diem-danh-7-guong-mat-hot-girl-xinh-dep-noi-bat-trong-thang-2-docx-1614441453135.jpeg")
                .addPrice(55000000.0)
                .addTitle("Điện thoại đẹp")
                .addQuantity(1)
                .build())
            list.add(Product.Builder()
                .addImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTk3O6wPUnvDOyGr-8Wbd6-lihTBhXQNfeCyg&usqp=CAU")
                .addPrice(20000000.0)
                .addTitle("Điện thoại đẹp")
                .addQuantity(1)
                .build())
            val cart = Cart(list)
            val mutableLiveData : MutableLiveData<Cart> = MutableLiveData()
            mutableLiveData.postValue(cart)
            return mutableLiveData
        }

        fun fakeOrder() : MutableLiveData<Order>? {
            val list = ArrayList<Product>()
            val product = Product.Builder()
                .addImage("https://upload.wikimedia.org/wikipedia/commons/0/0c/E-girl.png")
                .addPrice(44000000.0)
                .addTitle("Điện thoại")
                .addQuantity(1)
                .build()
            list.add(product)
            list.add(Product.Builder()
                .addImage("https://vnn-imgs-a1.vgcloud.vn/icdn.dantri.com.vn/2021/05/08/kimoanh-851-1620472406599.jpeg")
                .addPrice(23000000.0)
                .addTitle("Điện thoại đẹp")
                .addQuantity(1)
                .build())
            list.add(Product.Builder()
                .addImage("https://media.istockphoto.com/photos/lonly-young-woman-by-the-sea-picture-id840501418?b=1&k=20&m=840501418&s=170667a&w=0&h=2Smu2ybUDuZB120uKmQeEtegDXCTXHSLd3yL9GouwuQ=")
                .addPrice(42000000.0)
                .addTitle("Điện thoại đẹp")
                .addQuantity(1)
                .build())
            list.add(Product.Builder()
                .addImage("https://icdn.dantri.com.vn/thumb_w/640/2021/02/27/diem-danh-7-guong-mat-hot-girl-xinh-dep-noi-bat-trong-thang-2-docx-1614441453135.jpeg")
                .addPrice(55000000.0)
                .addTitle("Điện thoại đẹp")
                .addQuantity(1)
                .build())
            list.add(Product.Builder()
                .addImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTk3O6wPUnvDOyGr-8Wbd6-lihTBhXQNfeCyg&usqp=CAU")
                .addPrice(20000000.0)
                .addTitle("Điện thoại đẹp")
                .addQuantity(1)
                .build())
            val cart = Cart(list)
            val order = Order.Builder()
                .addCart(cart)
                .addPayment(COD())
                .build()
           val mutableLiveData : MutableLiveData<Order> = MutableLiveData()
            mutableLiveData.postValue(order)
            return mutableLiveData
        }

        fun fakeTypePayment() : MutableLiveData<ArrayList<Payment>> {
            var cod = COD()
            var momo = MoMo()
            var payments = ArrayList<Payment>()
            payments.add(cod)
            payments.add(momo)
            return MutableLiveData(payments)
        }

        fun fakeAccounts() : MutableLiveData<ArrayList<Account>> {
            var accounts = ArrayList<Account>()
            var account = Account.Builder()
                .addName("Seven")
                .addAddress("20 Nguyen Trai")
                .addPhone("089xxxxxx")
                .addUsername("456")
                .addPassword("456")
                .build()
            Log.i("Fake",account.toString())
            accounts.add(account)
            accounts.add(account)
            accounts.add(account)
            return MutableLiveData(accounts)
        }

        fun fakeAccount() : Account {
//            var accounts = ArrayList<Account>()
            var account = Account.Builder()
                .addName("Seven")
                .addAddress("20 jhgj Nguyen Trai")
                .addPhone("089xxxxxx")
                .addUsername("456")
                .addPassword("456")
                .build()
            Log.i("Fake",account.toString())
//            accounts.add(account)
//            accounts.add(account)
//            accounts.add(account)
//            return MutableLiveData(accounts)
            return account
        }

        fun fakeShipInfos() : MutableLiveData<ArrayList<ShipInfo>> {
            val shipInfo = ShipInfo.Builder()
                .addAddress("Hoa Thanh Ti")
                .addName("Nick Seven")
                .addPhone("089xxxx")
                .build()
            var shipInfos = ArrayList<ShipInfo>()
            for (i in 0..5) {
                shipInfos.add(shipInfo)
            }
            return MutableLiveData(shipInfos)
        }
    }
}