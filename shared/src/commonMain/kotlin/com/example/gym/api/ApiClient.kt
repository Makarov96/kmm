package com.example.gym.api

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

//val httpClient = HttpClient(){
//    install(ContentNegotiation){
//        json(Json {
//            ignoreUnknownKeys = true
//            prettyPrint = true
//
//
//        })
//    }
//}

val httpModule = module {
   single { HttpClient(){
       install(ContentNegotiation){
           json(Json {
               ignoreUnknownKeys = true
               prettyPrint = true


           })
       }
   } }
}