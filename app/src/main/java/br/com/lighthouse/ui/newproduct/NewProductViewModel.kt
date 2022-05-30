package br.com.lighthouse.ui.newproduct
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.lighthouse.MyApp
import br.com.lighthouse.data.Product
import br.com.lighthouse.data.ProductDAO
import javax.inject.Inject


class NewProductViewModel
(application: Application): AndroidViewModel(application)   {

    @Inject
    lateinit var productDao: ProductDAO

    lateinit var allProductsList: MutableLiveData<List<Product>>

    init {
        println("================================NEW products VM")
        (application as MyApp).getAppComponent().injectNewProduct(this)
        allProductsList = MutableLiveData()
        getAllRecords()
    }

    fun getRecordsObserver(): MutableLiveData<List<Product>> {
        return allProductsList
    }

    fun getAllRecords(){
        val list = productDao.getAllProducts()
        allProductsList.postValue(list)
    }

    fun insertRecord( product: Product ){
        productDao.insert(product)
    }

}