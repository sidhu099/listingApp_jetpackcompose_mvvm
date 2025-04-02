package com.example.jetpackcompose_poc.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcompose_poc.data.model.Product
import com.example.jetpackcompose_poc.data.remote.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class ProductViewModel(private val repository: ProductRepository): ViewModel() {

    val products: StateFlow<List<Product>> = repository.getProducts().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())


}