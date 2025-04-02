package com.example.jetpackcompose_poc.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcompose_poc.data.model.Product
import com.example.jetpackcompose_poc.data.remote.ApiService
import com.example.jetpackcompose_poc.data.remote.NetworkModule
import com.example.jetpackcompose_poc.data.remote.ProductRepository
import com.example.jetpackcompose_poc.data.remote.datasource.RemoteDataSource
import com.example.jetpackcompose_poc.ui.theme.JetpackCompose_pocTheme
import com.example.jetpackcompose_poc.viewmodel.ProductViewModel
import com.example.jetpackcompose_poc.viewmodel.ProductViewModelFactory

class MainActivity : ComponentActivity() {

    private val viewModel: ProductViewModel by viewModels(factoryProducer = {
        ProductViewModelFactory(
            ProductRepository(
                RemoteDataSource(
                    NetworkModule.retrofit.create(ApiService::class.java)
                )
            )
        )
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackCompose_pocTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(viewModel)
                }
            }
        }

    }
}

@Composable
fun MainScreen(viewModel: ProductViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "product_list") {
        composable("product_list") {
            ProductListingScreen(viewModel) {
                print("Navigate to details screen")
                navController.navigate("details_screen")
            }
        }
        composable("details_screen") {
            DetailScreen(text = "Detail screen")
        }
    }
}

@Composable
fun ProductListingScreen(viewModel: ProductViewModel, onClickAction: () -> Unit = {}) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        val products by viewModel.products.collectAsState()
        ItemList(products = products) {
            onClickAction()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(text: String?) {
    val navController = rememberNavController();
    Scaffold(topBar = { AppBar(title = "Details Screen", onBackClick = {
        navController.popBackStack();
    }) }) {
        Surface {
            Column {
                Text(text = text ?: "Details Screen")

            }
        }
    }
}

@Composable
fun ItemList(products: List<Product>, onClickAction: () -> Unit) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        items(products) { product ->
            Card(modifier = Modifier
                .fillMaxSize()
                .padding(start = 7.0.dp, end = 7.0.dp, top = 7.0.dp)
                .clickable {
                    onClickAction.invoke()
                }
            ) {
                Text(text = product.title ?: "Unknown", modifier = Modifier.padding(20.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreen() {
    Column {
        Text(text = "Details Screen")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String,
    onBackClick: () -> Unit
) {
    TopAppBar(title = { Text(text = title) }, navigationIcon = {
        Icon(
            Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .clickable { onBackClick() }
        )
    }, actions = {

    })
}


@Preview(showBackground = true)
@Composable
fun ItemListPreview() {
    JetpackCompose_pocTheme {
        ItemList(
            products = listOf(
                Product(
                    1,
                    "Product 1",
                    "Description 1",
                    "",
                    0.0,
                    0.0,
                    0.0,
                    5,
                    emptyArray(),
                    ""
                ),
                Product(
                    2,
                    "Product 2",
                    "Description 2",
                    "",
                    0.0,
                    0.0,
                    0.0,
                    6,
                    emptyArray(),
                    ""
                )
            )
        ) {}
    }
}
