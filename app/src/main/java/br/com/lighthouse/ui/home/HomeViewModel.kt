package br.com.lighthouse.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.lighthouse.MyApp
import br.com.lighthouse.data.Product
import br.com.lighthouse.data.ProductDAO
import javax.inject.Inject

class HomeViewModel (application: Application): AndroidViewModel(application)   {

    var searchQuery = ""

    @Inject
    lateinit var productDao: ProductDAO

    var allProductsList: MutableLiveData<List<Product>>

    init {
        (application as MyApp).getAppComponent().injectHome(this)
        allProductsList = MutableLiveData()
        getProducts()
    }

    fun getRecordsObserver(): MutableLiveData<List<Product>> {
        return allProductsList
    }

    fun getAllRecords(){
        val list = productDao.getAllProducts()
        allProductsList.postValue(list)
    }

    fun getProducts(){
        val list = productDao.getProduct(searchQuery)
        allProductsList.postValue(list)
    }

}