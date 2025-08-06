package com.omdacode.fakestoreproduct.data.repository

import com.omdacode.fakestoreproduct.data.remote.FakeStoreApi
import com.omdacode.fakestoreproduct.domain.model.Product
import com.omdacode.fakestoreproduct.domain.repository.ProductRepository

class ProductRepositoryImpl {
    class ProductRepositoryImpl(
        private val api: FakeStoreApi
    ) : ProductRepository {

        override suspend fun getProducts(): Result<List<Product>> {
            return try {
                Result.success(api.getProducts())
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}