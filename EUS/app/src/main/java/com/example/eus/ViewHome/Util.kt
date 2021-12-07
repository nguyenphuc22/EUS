package com.example.eus.ViewHome

import androidx.lifecycle.MutableLiveData
import com.example.eus.ODT.Product
import java.text.NumberFormat

class Util {
    companion object {
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
            var mutableLiveData : MutableLiveData<List<Product>> = MutableLiveData()
            var list = ArrayList<Product>()
            list.add(Product("https://upload.wikimedia.org/wikipedia/commons/0/0c/E-girl.png",44000000.0,"Điện thoại "))
            list.add(Product("https://vnn-imgs-a1.vgcloud.vn/icdn.dantri.com.vn/2021/05/08/kimoanh-851-1620472406599.jpeg",23000000.0,"Điện thoại đẹp"))
            list.add(Product("https://media.istockphoto.com/photos/lonly-young-woman-by-the-sea-picture-id840501418?b=1&k=20&m=840501418&s=170667a&w=0&h=2Smu2ybUDuZB120uKmQeEtegDXCTXHSLd3yL9GouwuQ=",42000000.0,"Điện thoại đẹp"))
            list.add(Product("https://icdn.dantri.com.vn/thumb_w/640/2021/02/27/diem-danh-7-guong-mat-hot-girl-xinh-dep-noi-bat-trong-thang-2-docx-1614441453135.jpeg",55000000.0,"Điện thoại đẹp"))
            list.add(Product("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTk3O6wPUnvDOyGr-8Wbd6-lihTBhXQNfeCyg&usqp=CAU",20000000.0,"Điện thoại đẹp"))
            mutableLiveData.value = list
            return mutableLiveData
        }
    }
}