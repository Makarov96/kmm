@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.gym.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.gym.api.httpModule
import com.example.gym.data.Product
import com.example.gym.injector.Injector
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin


class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {


            modules(    Injector.appModule() + homeViewModule)

        }


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
fun AndroidApp(products: List<Product>) {
      Scaffold(
          topBar = {
              TopAppBar(title = {
                  Text(text = "Welcome to KMM") },
                  colors = TopAppBarColors(
                      containerColor = Color.Black,
                      actionIconContentColor = Color.Red,
                      scrolledContainerColor = Color.Transparent,
                      titleContentColor = Color.White,
                      navigationIconContentColor = Color.Cyan,
                  ),


              )
          }
      ) {

          it ->
         LazyColumn(modifier = Modifier.padding(it)) {
                items(products, key = {product -> product.id.toString()}){
                    println(it.images.first())
                    Card(shape = RoundedCornerShape(10.dp), modifier =
                    Modifier
                        .fillParentMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                        ) {
                       Row {

                          AsyncImage(model = it.images.first(), contentDescription ="None",
                              contentScale = ContentScale.FillBounds,
                              modifier = Modifier
                                  .size(100.dp,100.dp)
                                  .clip(RoundedCornerShape(10.dp))


                          )

                               Column(modifier = Modifier.padding(20.dp)) {
                               Text(text = "Product brand: ${it.brand}")
                               Text(text = "Product price: ${it.price}")

                           }
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
        AndroidApp(products = listOf(
            Product(title = "Sample", id = 0, brand = "Gucci",
                price = 200, stock = 10, discountPercentage = 2.0,
                images = listOf("https://cdn.pixabay.com/photo/2016/04/15/08/04/strawberry-1330459_640.jpg"),
                rating = 2.0, category = "None",
                thumbnail = "", description = "Hello",
            )
        )
        )
    }
}
