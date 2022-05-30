package br.com.lighthouse.ui.productui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.lighthouse.R
import br.com.lighthouse.data.Product
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val product : Product? = intent.getParcelableExtra<Product>("product")

        txtTitle.setText(product?.productName)
        txtQuantity.setText("${product?.quantity} units")

        //println(product)

    }
}