package com.example.jetpackcompose_poc.data.remote

import com.example.jetpackcompose_poc.data.model.AllProductsResponse
import com.example.jetpackcompose_poc.data.model.Product
import retrofit2.http.GET

interface ApiService {

    @GET("products")
    suspend fun getProducts(): AllProductsResponse

}