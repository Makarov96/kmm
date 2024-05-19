package com.example.gym.api

import com.example.gym.data.Product
import com.example.gym.data.ProductFromJson
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.flow

class HomeRepository {

    suspend fun getProducts(): ProductFromJson{
        val response = httpClient.get("https://dummyjson.com/products")
        return response.body()

    }

    suspend fun getProductsWithoutFlow(): List<Product>{
        return getProducts().products
    }

    fun getProductFlow()= flow{

        emit(getProducts().products)
    }
}