package com.example.gym.injector

import com.example.gym.api.homeModule
import com.example.gym.api.httpModule
import org.koin.core.context.startKoin

class Injector {

 companion object{
     fun appModule() = listOf(httpModule, homeModule)
 }

}