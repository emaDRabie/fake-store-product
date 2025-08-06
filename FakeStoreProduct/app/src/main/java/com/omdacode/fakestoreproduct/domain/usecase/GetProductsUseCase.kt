package com.omdacode.fakestoreproduct.domain.usecase

import com.omdacode.fakestoreproduct.domain.model.Product
import com.omdacode.fakestoreproduct.domain.repository.ProductRepository

class GetProductsUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): Result<List<Product>> = repository.getProducts()
}