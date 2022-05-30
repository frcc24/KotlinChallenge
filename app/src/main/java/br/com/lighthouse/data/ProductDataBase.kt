package br.com.lighthouse.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Product::class], version = 1)
abstract class ProductDataBase : RoomDatabase() {

    abstract fun getProductsDa0() : ProductDAO

    companion object {
        private var db_instance: ProductDataBase? = null

        fun getProductDBInstance(context: Context) :ProductDataBase{
            if(db_instance == null){
                db_instance = Room.databaseBuilder<ProductDataBase>(
                    context.applicationContext, ProductDataBase::class.java, "products_db")
                    .allowMainThreadQueries()
                    .build()
            }
            return db_instance!!
        }
    }

}