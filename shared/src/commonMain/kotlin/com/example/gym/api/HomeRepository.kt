package com.example.gym.api

import com.example.gym.data.Product
import com.example.gym.data.ProductFromJson
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.flow
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

class HomeRepository(val client: HttpClient) {

    suspend fun getProducts(): ProductFromJson{
        val response = client.get("https://dummyjson.com/products")
        return response.body()

    }

    suspend fun getProductsWithoutFlow(): List<Product>{
        return getProducts().products
    }

    fun getProductFlow()= flow{

        emit(getProducts().products)
    }
}


val homeModule = module {

    singleOf(::HomeRepository)
}