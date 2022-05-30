package br.com.lighthouse.di

import android.app.Application
import android.content.Context
import br.com.lighthouse.data.ProductDAO
import br.com.lighthouse.data.ProductDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(val application: Application) {

    @Singleton
    @Provides
    fun getProductsDao(productDataBase: ProductDataBase ) : ProductDAO {
        return productDataBase.getProductsDa0()
    }

    @Singleton
    @Provides
    fun getRoomDBInstance(): ProductDataBase {
        return ProductDataBase.getProductDBInstance(provideAppContext())
    }

    @Singleton
    @Provides
    fun provideAppContext(): Context {
        return application.applicationContext
    }
}