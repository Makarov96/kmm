package com.example.gym.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gym.api.HomeRepository
import com.example.gym.data.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel:ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(listOf())
    val product = _products.asStateFlow()

    private val homeRepository: HomeRepository = HomeRepository()
    init {
       viewModelScope.launch {
           homeRepository.getProductFlow().collect{ products ->
                _products.update {
                    it + products
                }
           }
       }
    }
}