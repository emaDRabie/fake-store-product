package com.omdacode.fakestoreproduct.presentation.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.omdacode.fakestoreproduct.domain.model.Product
import com.omdacode.fakestoreproduct.ui.theme.Goldy
import com.omdacode.fakestoreproduct.ui.theme.Secondary

@Composable
fun ProductDetailCard(
    product: Product,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Secondary,
            contentColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(product.images.firstOrNull()),
                contentDescription = product.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .border(1.dp, Color.White, RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp)),
            )
            Spacer(Modifier.height(20.dp))
            Text(
                product.title, style = TextStyle(
                    color = Color.White,
                    fontSize = 26.sp,
                    lineHeight = 30.sp,
                    fontWeight = FontWeight.W600
                ), maxLines = 3
            )
            Text(
                text = "$${product.price}",
                style = TextStyle(
                    fontSize = 22.sp,
                    color = Goldy,
                    fontWeight = FontWeight.Thin,
                ),
            )
            Spacer(Modifier.height(14.dp))
            Text(
                product.description,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify
            )
        }
    }
}