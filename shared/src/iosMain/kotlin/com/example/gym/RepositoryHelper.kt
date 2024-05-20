package com.example.gym

import com.example.gym.api.HomeRepository
import com.example.gym.data.Product
import com.example.gym.data.ProductFromJson
import com.example.gym.injector.Injector
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class RepositoryHelper: KoinComponent {
    private val repository:HomeRepository by inject()
    suspend fun products() : List<Product> = repository.getProductsWithoutFlow()
     fun productsFlow() : Flow<List<Product>> = repository.getProductFlow()

    suspend fun modelFromJson() : ProductFromJson = repository.getProducts()

}

fun initKoin(){

    startKoin {
       modules(  Injector.appModule())
    }
}
