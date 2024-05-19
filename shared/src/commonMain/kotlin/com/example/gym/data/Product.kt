package com.example.gym.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerialName("id")
    val id: Int,
    @SerialName("brand")
    val brand: String,
    @SerialName("category")
    val category: String,
    @SerialName("description")
    val description: String,
    @SerialName("discountPercentage")
    val discountPercentage: Double,
    @SerialName("images")
    val images: List<String>,
    @SerialName("price")
    val price: Int,
    @SerialName("rating")
    val rating: Double,
    @SerialName("stock")
    val stock: Int,
    @SerialName("thumbnail")
    val thumbnail: String,
    @SerialName("title")
    val title: String
)