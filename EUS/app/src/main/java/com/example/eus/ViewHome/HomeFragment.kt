package com.example.eus.ViewHome

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eus.ODT.Product
import com.example.eus.R
import com.example.eus.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var adapterCategory: AdapterCategory
    private lateinit var adapterProduct: AdapterProduct

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        adapterCategory = AdapterCategory()
        fakeCategory().observe(viewLifecycleOwner, Observer {
            adapterCategory.setData(it)
        })
        binding.recyclerCategory.layoutManager = GridLayoutManager(context,5)
        binding.recyclerCategory.adapter = adapterCategory

        adapterProduct = AdapterProduct()
        fakeData().observe(viewLifecycleOwner, Observer {
            adapterProduct.setProduct(it)
        })

        binding.recyclerListProduct.layoutManager = GridLayoutManager(context,2)
        binding.recyclerListProduct.adapter = adapterProduct

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.itemProfile -> {
                this.findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun fakeData() : MutableLiveData<List<Product>> {
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

    private fun fakeCategory() : MutableLiveData<List<String>> {
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

}