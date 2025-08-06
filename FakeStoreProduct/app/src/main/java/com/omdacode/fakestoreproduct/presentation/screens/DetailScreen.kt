package com.omdacode.fakestoreproduct.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.omdacode.fakestoreproduct.di.AppModule
import com.omdacode.fakestoreproduct.presentation.ProductUiState

@Composable
fun DetailScreen(productId: Int?) {
    val products =
        (AppModule.productViewModel.uiState.value as? ProductUiState.Success)?.products
            ?: emptyList()
    val product = products.find { it.id == productId }
    if (product != null) {
        ProductDetailCard(product = product, modifier = Modifier.fillMaxSize())
    } else {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Product not found", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    val product = com.omdacode.fakestoreproduct.domain.model.Product(
        id = 1,
        title = "Majestic Mountain Graphic T-Shirt",
        slug = "majestic-mountain-graphic-t-shirt",
        price = 15,
        description = "This is a sample product description for the preview.",
        category = com.omdacode.fakestoreproduct.domain.model.Product.Category(
            id = 1,
            name = "Sample Category",
            slug = "sample-category",
            image = "https://via.placeholder.com/150",
            creationAt = "2023-01-01T00:00:00Z",
            updatedAt = "2023-01-01T00:00:00Z"
        ),
        images = listOf("https://i.imgur.com/QkIa5tT.jpeg"),
        creationAt = "2023-01-01T00:00:00Z",
        updatedAt = "2023-01-01T00:00:00Z"
    )
    ProductDetailCard(product = product, modifier = Modifier.fillMaxSize())
}