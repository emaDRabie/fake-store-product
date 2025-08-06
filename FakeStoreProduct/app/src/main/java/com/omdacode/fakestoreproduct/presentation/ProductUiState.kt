package com.omdacode.fakestoreproduct.presentation

import com.omdacode.fakestoreproduct.domain.model.Product

sealed class ProductUiState {
    data object Loading : ProductUiState()
    data class Success(val products: List<Product>) : ProductUiState()
    data class Error(val message: String) : ProductUiState()
}