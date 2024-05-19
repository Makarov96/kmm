package com.example.gym.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.gym.data.Product


class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val products = homeViewModel.product.collectAsStateWithLifecycle(
                lifecycleOwner = androidx.compose.ui.platform.LocalLifecycleOwner.current
            )
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) { AndroidApp(products.value) }
            }
        }
    }
}

@Composable
fun AndroidApp(products:List<Product>) {
      Scaffold {
          it ->
         LazyColumn(modifier = Modifier.padding(it)) {
                items(products, key = {product -> product.id.toString()}){
                    Card {
                        Column {
                            Text(text = "Product brand: ${it.brand}")
                            Text(text = "Product price: ${it.price}")

                        }
                    }
                }
         }
      }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        AndroidApp(listOf())
    }
}
