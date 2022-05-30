package br.com.lighthouse.ui.productui

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.lighthouse.R
import br.com.lighthouse.data.Product
import kotlinx.android.synthetic.main.activity_product.*
import java.lang.Exception

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val product : Product? = intent.getParcelableExtra<Product>("product")

        txtTitle.setText(product?.productName)
        txtQuantity.setText("${product?.quantity} units")


        val imgUri: Uri = Uri.parse(product?.image)
        try {
            if(product?.image != "") {
                imageView2.setImageURI( imgUri )
            } else{
                imageView2.setImageResource( com.google.android.material.R.drawable.material_ic_calendar_black_24dp )
            }

        }catch (e: Exception){
            println(e.message)
            imageView2.setImageResource( com.google.android.material.R.drawable.ic_keyboard_black_24dp )
        }


        //println(product)

    }
}