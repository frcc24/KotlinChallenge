package br.com.lighthouse.ui.home.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.lighthouse.R
import br.com.lighthouse.data.Product
import br.com.lighthouse.databinding.ProductItemBinding


class ProductsAdapter : ListAdapter<Product, ProductsAdapter.ProductsViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val currentProduct = getItem(position)
        holder.bind(currentProduct)
    }

    class ProductsViewHolder(private val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(product: Product){
            binding.apply {
                productImageItem.setImageResource( R.drawable.ic_launcher_background )
                productNameItem.text = product.productName
                productQtdItem.text  = product.quantity.toString()

            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
           return oldItem == newItem
        }
    }
}