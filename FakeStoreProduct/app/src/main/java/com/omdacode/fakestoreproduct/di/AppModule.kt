package com.omdacode.fakestoreproduct.di

import com.omdacode.fakestoreproduct.data.remote.RetrofitInstance
import com.omdacode.fakestoreproduct.data.repository.ProductRepositoryImpl
import com.omdacode.fakestoreproduct.domain.usecase.GetProductsUseCase
import com.omdacode.fakestoreproduct.presentation.ProductViewModel

object AppModule {
    private val repository = ProductRepositoryImpl.ProductRepositoryImpl(RetrofitInstance.api)
    private val getProductsUseCase = GetProductsUseCase(repository)
    val productViewModel = ProductViewModel(getProductsUseCase)
}