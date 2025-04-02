package com.example.jetpackcompose_poc.data.remote.datasource

import com.example.jetpackcompose_poc.data.model.Product
import com.example.jetpackcompose_poc.data.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getProducts(): List<Product> {
       return withContext(Dispatchers.IO){
           apiService.getProducts().products
        }
    }

}