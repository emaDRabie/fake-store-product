package com.omdacode.fakestoreproduct.domain.repository

import com.omdacode.fakestoreproduct.domain.model.Product

interface ProductRepository {
    suspend fun getProducts(): Result<List<Product>>
}