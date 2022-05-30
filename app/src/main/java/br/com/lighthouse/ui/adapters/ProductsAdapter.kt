package br.com.lighthouse.ui.home.adapters

import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.lighthouse.R
import br.com.lighthouse.data.Product
import br.com.lighthouse.databinding.ProductItemBinding
import java.lang.Exception


class ProductsAdapter( private val listener: onItemClickListener) :
    ListAdapter<Product, ProductsAdapter.ProductsViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val currentProduct = getItem(position)
        holder.bind(currentProduct)
    }

    inner class ProductsViewHolder(private val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root){
      init {
          binding.apply {
              root.setOnClickListener{
                  val position = adapterPosition
                  if(position != RecyclerView.NO_POSITION){
                      val product = getItem(position)
                      listener.onItemClick(product)
                  }
              }
          }
      }
        fun bind(product: Product){
            binding.apply {

                val imgUri: Uri = Uri.parse(product.image)
                try {
                    if(product.image != "") {
                        productImageItem.setImageURI( imgUri )
                    } else{
                        productImageItem.setImageResource( com.google.android.material.R.drawable.material_ic_calendar_black_24dp )
                    }

                }catch (e: Exception){
                    println(e.message)
                    productImageItem.setImageResource( com.google.android.material.R.drawable.ic_keyboard_black_24dp )
                }

                productNameItem.text = product.productName
                productQtdItem.text  = product.quantity.toString()
            }
        }
    }

    interface onItemClickListener {
        fun onItemClick(product: Product)
    }

    class DiffCallback : DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product) =
             oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Product, newItem: Product) =
            oldItem == newItem
    }
}