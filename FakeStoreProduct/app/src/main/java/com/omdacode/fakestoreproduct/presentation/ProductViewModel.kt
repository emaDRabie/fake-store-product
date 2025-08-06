package com.omdacode.fakestoreproduct.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omdacode.fakestoreproduct.domain.usecase.GetProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProductUiState>(ProductUiState.Loading)
    val uiState: StateFlow<ProductUiState> = _uiState

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        _uiState.value = ProductUiState.Loading
        viewModelScope.launch {
            val result = getProductsUseCase()
            _uiState.value = result.fold(
                onSuccess = { ProductUiState.Success(it) },
                onFailure = { ProductUiState.Error(it.localizedMessage ?: "Unknown Error") }
            )
        }
    }
}