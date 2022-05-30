package br.com.lighthouse.di

import br.com.lighthouse.ui.home.HomeViewModel
import br.com.lighthouse.ui.newproduct.NewProductViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface App  {

    fun injectHome( homeViewModel: HomeViewModel)
    fun injectNewProduct( newProductViewModel: NewProductViewModel)
}