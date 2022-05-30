package br.com.lighthouse.data

import androidx.room.*

@Dao
interface  ProductDAO {


    @Query("SELECT * FROM product_table")
    fun getAllProducts(): List<Product>?

    @Query("SELECT * FROM product_table WHERE productName LIKE '%' || :searchQuery || '%' ")
    fun getProduct(searchQuery: String): List<Product>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: Product)

    @Delete
    fun delete(product: Product)

    @Update
    fun update(product: Product)

}