package com.example.jetpackcompose_poc.data.remote

import com.example.jetpackcompose_poc.data.model.Product
import com.example.jetpackcompose_poc.data.remote.datasource.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow

class ProductRepository(private val remoteDataSource: RemoteDataSource) {

   fun getProducts(): Flow<List<Product>> = flow {
       var response = remoteDataSource.getProducts()
        emit(response)
    }

}