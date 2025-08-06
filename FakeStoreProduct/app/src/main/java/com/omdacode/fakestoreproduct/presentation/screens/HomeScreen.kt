package com.omdacode.fakestoreproduct.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.omdacode.fakestoreproduct.domain.model.Product
import com.omdacode.fakestoreproduct.presentation.ProductUiState
import com.omdacode.fakestoreproduct.presentation.ProductViewModel
import com.omdacode.fakestoreproduct.ui.theme.Goldy
import com.omdacode.fakestoreproduct.ui.theme.Secondary
import com.omdacode.fakestoreproduct.ui.theme.Primary

@Composable
fun HomeScreen(
    viewModel: ProductViewModel,
    onProductClick: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is ProductUiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is ProductUiState.Success -> {
            val products = (uiState as ProductUiState.Success).products
            LazyColumn {
                items(products.size) { i ->
                    val product = products[i]
                    ProductCard(product, onClick = { onProductClick(product.id) })
                }
            }
        }

        is ProductUiState.Error -> {
            val message = (uiState as ProductUiState.Error).message
            Snackbar { Text(message) }
        }
    }
}

@Composable
fun ProductCard(product: Product, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Secondary,
            contentColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .padding(16.dp)
            .clickable(onClick = onClick)
            .fillMaxWidth()
    ) {
        Row(
            Modifier
                .padding(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(product.images.firstOrNull()),
                contentDescription = product.title,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(128.dp)
                    .border(1.dp, Color.White, RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp)),
            )
            Spacer(Modifier.width(16.dp))
            Column {
                Text(
                    product.title, style = TextStyle(
                        color = Color.White,
                        fontSize = 26.sp,
                        lineHeight = 30.sp,
                        fontWeight = FontWeight.W600
                    ), maxLines = 3
                )
                Text(
                    "$${product.price}", style = TextStyle(
                        fontSize = 22.sp,
                        color = Goldy,
                        fontWeight = FontWeight.Thin
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val product = Product(
        id = 1,
        title = "Sample Product",
        slug = "sample-product",
        price = 99,
        description = "This is a sample product description.",
        category = Product.Category(
            id = 1,
            name = "Sample Category",
            slug = "sample-category",
            image = "https://via.placeholder.com/150",
            creationAt = "2023-01-01T00:00:00Z",
            updatedAt = "2023-01-01T00:00:00Z"
        ),
        images = listOf(
            "https://placehold.co/600x400",
            "https://placehold.co/600x400"
        ),
        creationAt = "2023-01-01T00:00:00Z",
        updatedAt = "2023-01-01T00:00:00Z"
    )
    ProductCard(
        product = product,
        onClick = {}
    )

}