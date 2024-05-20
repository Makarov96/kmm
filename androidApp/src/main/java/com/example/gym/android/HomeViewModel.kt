package com.example.gym.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.gym.api.HomeRepository
import com.example.gym.api.httpModule
import com.example.gym.data.Product
import com.example.gym.injector.Injector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf

import org.koin.dsl.module



class HomeViewModel(private val repository: HomeRepository):ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(listOf())
    val product = _products.asStateFlow()
    init {
       viewModelScope.launch {
           repository.getProductFlow().collect{ products ->
                _products.update {
                    it + products
                }
           }
       }
    }
}

val homeViewModule = module {


    viewModelOf(::HomeViewModel)
}



