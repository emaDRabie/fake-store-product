package com.omdacode.fakestoreproduct.data.remote

import com.omdacode.fakestoreproduct.domain.model.Product
import retrofit2.http.GET

interface FakeStoreApi {
    @GET("https://api.escuelajs.co/api/v1/products")
    suspend fun getProducts(): List<Product>
}